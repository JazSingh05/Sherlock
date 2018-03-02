package com.example.android.sherlock.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.sherlock.R;
import com.example.android.sherlock.database.Database;

import butterknife.BindView;
import butterknife.ButterKnife;


//lets go travis
public class SplashScreen extends AppCompatActivity {
    //This is how you bind a view in XML to a global var in the class
    @BindView(R.id.logo)
    ImageView splashImage;
    @BindView(R.id.loading)
    TextView loadingText;
    @BindView(R.id.title_text)
    TextView titleText;


    Database db;
    private RotateAnimation rotate;
    private AlphaAnimation fadeIn;
    private ScaleAnimation scaleIn;
    private String[] loadTexts = {"Loading.", "Loading..", "Loading...", "Weeeeeeeeee!"};
    private int textIdx = 0;
    //These methods should be implemented or at least stubbed out and call super in every activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        buildAnimations();

        this.db = new Database(this);
        if(!db.hasData()){
            db.addTestData();
        }else {
            Log.d("SPLASH", "data detected, not adding test data");
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        //make sure you alwasy call this function in onStart, not before, not after
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        titleText.startAnimation(fadeIn);
    }

    private void buildAnimations() {
        //fadeIn animation
        fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setStartOffset(100);
        fadeIn.setDuration(1000);
        fadeIn.setInterpolator(new DecelerateInterpolator());
        fadeIn.setAnimationListener(titleListener);
        fadeIn.setFillAfter(true);
        //scale in animation
        scaleIn = new ScaleAnimation(0f, 1f, 0f, 1f, Animation.RELATIVE_TO_SELF, .5f, Animation.RELATIVE_TO_SELF, .5f);
        scaleIn.setDuration(500);
        scaleIn.setInterpolator(new AnticipateOvershootInterpolator(1f));
        scaleIn.setFillAfter(true);
        scaleIn.setAnimationListener(logoAppearListener);
        //rotate animation
        rotate = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, .5f, Animation.RELATIVE_TO_SELF, .5f);
        rotate.setDuration(500);
        rotate.setInterpolator(new AccelerateDecelerateInterpolator());
        rotate.setRepeatMode(Animation.RESTART);
        rotate.setRepeatCount(4);
        rotate.setAnimationListener(spinListener);
    }

    //listener for the irst animation, the fade in of the app title
    private Animation.AnimationListener titleListener = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {
            titleText.setVisibility(View.VISIBLE);
        }
        @Override
        public void onAnimationEnd(Animation animation) {
            splashImage.startAnimation(scaleIn);
        }
        @Override
        public void onAnimationRepeat(Animation animation) {
            //don't care for this animation
        }
    };

    //listener for the second thing that happens, the logo scaling in
    private Animation.AnimationListener logoAppearListener = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {
            splashImage.setVisibility(View.VISIBLE);
        }
        @Override
        public void onAnimationEnd(Animation animation) {
            splashImage.startAnimation(rotate);
        }
        @Override
        public void onAnimationRepeat(Animation animation) {
            //dont care, animation doesnt repeat
        }
    };

    //listener for the third animation, the spinning logo and the loading text
    private Animation.AnimationListener spinListener = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {
            loadingText.setVisibility(View.VISIBLE);
        }
        @Override
        public void onAnimationEnd(Animation animation) {
            Intent intent = new Intent(SplashScreen.this, SearchActivity.class);
            startActivity(intent);
            SplashScreen.this.finish();
        }
        @Override
        public void onAnimationRepeat(Animation animation) {
            loadingText.setText(loadTexts[textIdx++]);
        }
    };

}
