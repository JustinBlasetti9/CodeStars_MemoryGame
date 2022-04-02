package com.example.a1022projecttest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenu extends AppCompatActivity {
    private Button button;
    private Button leadButton;
    private Button playButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        button = (Button) findViewById(R.id.button);
        leadButton = (Button) findViewById(R.id.leadButton);
        playButton = (Button) findViewById(R.id.playButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity2();
            }
        });
        leadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLeaderboards();
            }
        });
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPlay();
            }
        });
    }
    public void openActivity2(){
        Intent intent = new Intent(this, HowToPlay.class);
        startActivity(intent);
    }
    public void openLeaderboards(){
        Intent intent = new Intent(this, Leaderboard.class);
        intent.putExtra("scoreToBeSaved", -1);
        startActivity(intent);
    }
    public void openPlay(){
        Intent intent = new Intent(this, ModeSelect.class);
        startActivity(intent);
    }
}