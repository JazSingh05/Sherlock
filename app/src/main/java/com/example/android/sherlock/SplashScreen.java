package com.example.android.sherlock;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;


//lets go travis
public class SplashScreen extends AppCompatActivity {
    //This is how you bind a view in XML to a global var in the class
    @BindView(R.id.logo)
    ImageView splashImage;
    Database db;
    private RotateAnimation rotate;
    //These methods should be implemented or at least stubbed out and call super in every activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        rotate = new RotateAnimation(0, 1440, Animation.RELATIVE_TO_SELF, .5f, Animation.RELATIVE_TO_SELF, .5f);
        rotate.setDuration(1500);
        rotate.setInterpolator(new AccelerateDecelerateInterpolator());
        rotate.setRepeatMode(Animation.RESTART);
        rotate.setRepeatCount(Animation.INFINITE);
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
        splashImage.startAnimation(rotate);
    }

    public void mainScreen(View view){
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }

    public void Settings(View view){
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }


}
