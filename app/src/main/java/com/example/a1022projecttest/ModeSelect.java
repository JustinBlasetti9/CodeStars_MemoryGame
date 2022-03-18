package com.example.a1022projecttest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ModeSelect extends AppCompatActivity {
    private Button mainButton;
    private Button normalButton;
    private Button hardButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mode_select);

        mainButton = (Button) findViewById(R.id.modeToMainButton);
        normalButton = (Button) findViewById(R.id.normalSelectButton);
        hardButton = (Button) findViewById(R.id.hardSelectButton);
        mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backToMain();
            }
        });
        normalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHardGame();
            }
        });
        hardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNormalGame();
            }
        });
    }
    public void backToMain(){
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }
    public void openNormalGame(){
        Intent intent = new Intent(this, GameScreen.class);
        startActivity(intent);
    }
    public void openHardGame(){
        Intent intent = new Intent(this, GameScreen.class);
        startActivity(intent);
    }
}