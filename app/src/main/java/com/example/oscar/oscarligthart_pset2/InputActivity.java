
package com.example.oscar.oscarligthart_pset2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Oscar on 11/11/2016.
 */

public class InputActivity extends Activity {
    Story myStory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // create activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        // get which story is chosen in mainActivity
        Bundle extras = getIntent().getExtras();
        int story = extras.getInt("story", -500);

        // create variable inputstream
        InputStream is;

        // get story according to which button is pressed in MainActivity
        try{
            switch (story) {
                case 0:
                    is = getAssets().open("madlib0_simple.txt");
                    break;
                case 1:
                    is = getAssets().open("madlib1_tarzan.txt");
                    break;
                case 2:
                    is = getAssets().open("madlib2_university.txt");
                    break;
                case 3:
                    is = getAssets().open("madlib3_clothes.txt");
                    break;
                case 4:
                    is = getAssets().open("madlib4_dance.txt");
                    break;
                default:
                    throw new RuntimeException("Unknown story");
            }
        } catch (IOException e){
            e.printStackTrace();
            is = null;
        }

        // if story is not empty, prompt user for input of Placeholder
        if (is != null){
            myStory = new Story(is);

            // count amount of placeholders needed
            int placeHolderCount = myStory.getPlaceholderCount();

            // show how many placeholders are yet to be filled
            TextView inputTV = (TextView) findViewById(R.id.textView3);
            inputTV.setText("There are " + placeHolderCount + " words left!");

            // set hint of edittext to required placeholder type
            EditText wordET = (EditText) findViewById(R.id.editText);
            wordET.setHint(myStory.getNextPlaceholder());

            // remove text in the editText
            wordET.setText("");

        }





    }

    public void nextWord(View view){
        EditText wordET = (EditText) findViewById(R.id.editText);

        // check if user entered something
        if (wordET.getText().length() != 0)
        {
            // get entered word and place it into the empty place holder
            String word = wordET.getText().toString();
            myStory.fillInPlaceholder(word);

            // count amount of placeholders still to be filled and show that amount to user
            int placeHolderCount = myStory.getPlaceholderRemainingCount();
            TextView inputTV = (TextView) findViewById(R.id.textView3);
            inputTV.setText("There are " + placeHolderCount + " words left!");

            // set hint of edittext to required placeholder type
            wordET.setHint(myStory.getNextPlaceholder());

            // if there are no more placeholders remaining, turn to next activity
            if (myStory.getPlaceholderRemainingCount() == 0){
                toNext(view);
            }
            // if there are still placeholders remaining, empty text in hint
            else
                wordET.setText("");
        }

        // if user did not enter any characters, tell user to try again
        else
        {
            TextView inputTV = (TextView) findViewById(R.id.textView3);
            int placeHolderCount = myStory.getPlaceholderRemainingCount();
            inputTV.setText("Please enter a word! \n There are " + placeHolderCount + " words left!");
        }
    }


    public void toNext(View view) {
        // go to StoryActivity with the string of total story text
        Intent goToStory = new Intent(this, StoryActivity.class);
        goToStory.putExtra("myStory", myStory.toString());
        startActivity(goToStory);
        finish();
    }
}
