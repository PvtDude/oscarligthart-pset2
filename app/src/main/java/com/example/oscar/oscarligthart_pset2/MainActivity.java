package com.example.oscar.oscarligthart_pset2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    int story;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // create main screen
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onClick(View view) {
        // check which button was pressed and set story to an integer corresponding to that story
        // after setting story, run function forward()
        switch (view.getId()) {
            case R.id.Simple:
                story = 0;
                forward(view);
                break;
            case R.id.Tarzan:
                story = 1;
                forward(view);
                break;
            case R.id.University:
                story = 2;
                forward(view);
                break;
            case R.id.Clothes:
                story = 3;
                forward(view);
                break;
            case R.id.Dance:
                story = 4;
                forward(view);
                break;
            default:
                throw new RuntimeException("Unknown button ID");
        }
    }

    public void forward(View view) {
        // go to InputActivity with the integer corresponding to a certain story
        Intent goToInput = new Intent(view.getContext(), InputActivity.class);
        goToInput.putExtra("story", story);
        startActivity(goToInput);
        finish();
    }


}

