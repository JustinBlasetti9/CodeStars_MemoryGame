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
    private Button mainButton;
    private int scoreBeingSaved;
    private TextView leaderboard, currentScore;
    private int highScore;
    private String leaderText = "High Score: 0";

    /*
    public static final String sharedPrefs = "sharedPrefs";
    public static final String text = "sharedPrefs";
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leaderboard);
        this.leaderboard = findViewById(R.id.leaderboard);
        this.currentScore = findViewById(R.id.currentSavedScore);
        Bundle extras = getIntent().getExtras();
        this.scoreBeingSaved = extras.getInt("scoreToBeSaved");
        SharedPreferences preferences = getSharedPreferences("your_prefs", Activity.MODE_PRIVATE);
        int tempHighScore = preferences.getInt("HighScoreInt", 0);
        if (tempHighScore >this.highScore){
            this.highScore=tempHighScore;
        }
        //if accessed from main menu
        if (this.scoreBeingSaved < 0) {
            String tempText = preferences.getString("HighScore", "");
            if (tempText != null) {
                this.leaderText = tempText;
            }
            currentScore.setVisibility(View.INVISIBLE);
        }

        //if accessed from saving a score to the leaderboard
        else {
            if (this.scoreBeingSaved > this.highScore) {
                this.highScore = this.scoreBeingSaved;
            }
            this.leaderText = "High Score: " + this.highScore;
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("HighScore", this.leaderText);
            editor.putInt("HighScoreInt", this.highScore);
            editor.apply();
            currentScore.setVisibility(View.VISIBLE);
            currentScore.setText("Your current score: " + this.scoreBeingSaved);
        }

        leaderboard.setText(this.leaderText);


        mainButton = (Button) findViewById(R.id.leadToMainButton);
        mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backToMain();
            }
        });
    }

    public void backToMain() {
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }

}