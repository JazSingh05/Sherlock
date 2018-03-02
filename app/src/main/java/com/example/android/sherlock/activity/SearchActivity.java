package com.example.android.sherlock.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.sherlock.R;

/**
 * Created by abbin_j19pde on 1/24/2018.
 */

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_screen);
    }

    public void searchDatabase(View view)
    {

        EditText editText = (EditText) findViewById(R.id.editText);
        if(!editText.getText().toString().matches("") || editText.getText().toString().matches("")) {
            String DatabaseSearch = editText.getText().toString();
            Log.d("CHECKING STRING", "The string you entered: " + DatabaseSearch);
            Intent intent = new Intent(this, SearchResultActivity.class);
            intent.putExtra("SEARCH_TERM", DatabaseSearch);
            startActivity(intent);

        }
        else
        {
            Toast.makeText(this, "Enter in Something", Toast.LENGTH_SHORT).show();
        }






    }



}