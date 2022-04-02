package com.example.a1022projecttest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.content.SharedPreferences;
import android.widget.TextView;

import java.util.*;

public class Leaderboard extends AppCompatActivity {

    //initialize fields to store buttons and text view objects
    private Button mainButton;
    private int scoreBeingSaved; //score that the user wants to save to the leaderbaord
    private TextView leaderboard, currentScore;
    private int highScore; //the current high score achieved
    private String leaderText = "High Score: 0"; //text displayed on the leaderboard

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leaderboard);

        //store the textviews into their respective variables
        this.leaderboard = findViewById(R.id.leaderboard);
        this.currentScore = findViewById(R.id.currentSavedScore);

        //receive the passed value of the user's score into the scoreBeingSaved variable
        Bundle extras = getIntent().getExtras();
        this.scoreBeingSaved = extras.getInt("scoreToBeSaved");

        //create a sharedprefernces object to store the highscore between app/screen closure
        SharedPreferences preferences = getSharedPreferences("your_prefs", Activity.MODE_PRIVATE);
        //store the high score into a temporary variable
        int tempHighScore = preferences.getInt("HighScoreInt", 0);

        //if the temp high score is higher than the current highscore (since when this screen is loaded, this.highscore will be reinitialized to 0), set the high score to its correct value
        if (tempHighScore >this.highScore){
            this.highScore=tempHighScore;
        }
        //if the passed value is less than zero, leaderboard is being accessed from main menu
        if (this.scoreBeingSaved < 0) {
            //store the leaderboard text from sharedprefernces to a temporary variable
            String tempText = preferences.getString("HighScore", "");

            //if this data exists, set the leaderboard text to it
            if (tempText != null) {
                this.leaderText = tempText;
            }

            //since it is being accessed from main menu, there is no current score, so hide this text
            currentScore.setVisibility(View.INVISIBLE);
        }

        //if accessed from saving a score to the leaderboard
        else {

            //check if the score is higher than current highscore, if it is, set this to be new high score
            if (this.scoreBeingSaved > this.highScore) {
                this.highScore = this.scoreBeingSaved;
            }

            //set the new text for the leaderboard
            this.leaderText = "High Score: " + this.highScore;

            //store the highest score in the shared preferences object created earlier and apply it
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("HighScore", this.leaderText);
            editor.putInt("HighScoreInt", this.highScore);
            editor.apply();

            //show the current score text
            currentScore.setVisibility(View.VISIBLE);

            //display user's current score
            currentScore.setText("Your current score: " + this.scoreBeingSaved);
        }

        //update the displayed high score
        leaderboard.setText(this.leaderText);


        //store the return to main button and set an onclick attribute to return user to main menu screen
        mainButton = (Button) findViewById(R.id.leadToMainButton);
        mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backToMain();
            }
        });
    }

    //send user back to main menu
    public void backToMain() {
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }

}