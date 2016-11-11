package com.example.oscar.oscarligthart_pset2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.view.View;

/**
 * Created by Oscar on 11/11/2016.
 */

public class StoryActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // create story screen
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        // receive string of total story text from inputActivity
        Bundle extras = getIntent().getExtras();
        String myStory = extras.getString("myStory", "Error");

        // insert string of total text into textview
        TextView StoryTV = (TextView) findViewById(R.id.StoryView);
        StoryTV.setText(myStory);
    }

    public void toStart(View view){
        // if button is pressed, return to main screen
        Intent goToMainActivity = new Intent(this, MainActivity.class);
        startActivity(goToMainActivity);
        finish();
    }
}
