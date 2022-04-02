package com.example.a1022projecttest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ModeSelect extends AppCompatActivity {
    //initialize variables to store the buttons on screen
    private Button mainButton;
    private Button normalButton;
    private Button hardButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mode_select);

        //store the buttons on screen into their respective fields
        mainButton = (Button) findViewById(R.id.modeToMainButton);
        normalButton = (Button) findViewById(R.id.normalSelectButton);
        hardButton = (Button) findViewById(R.id.hardSelectButton);

        //set on click attributes for the buttons for them to call their respective functions
        mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backToMain();
            }
        });
        normalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNormalGame();
            }
        });
        hardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHardGame();
            }
        });
    }

    //sends user to main menu
    public void backToMain(){
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }

    //sends user to the game screen
    public void openNormalGame(){
        Intent intent = new Intent(this, GameScreen.class);
        intent.putExtra("modeSelected", "normal"); //passes to the game screen that normal mode was selected
        startActivity(intent);
    }

    //sends user to game screen
    public void openHardGame(){
        Intent intent = new Intent(this, GameScreen.class);
        intent.putExtra("modeSelected", "hard"); //passes to the game screen that hard mode was selected
        startActivity(intent);
    }
}