package com.example.a1022projecttest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Leaderboard extends AppCompatActivity {
    private Button mainButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leaderboard);

        mainButton = (Button) findViewById(R.id.leadToMainButton);
        mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backToMain();
            }
        });
    }
    public void backToMain(){
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }
}