package com.example.a1022projecttest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HowToPlay extends AppCompatActivity {

    //initialize a variable to store the button object
    private Button button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.how_to_play);

        //store the button to the variable and set an onclick listener for it to open main menu when clicked
        button2 = (Button) findViewById(R.id.returnButton);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity1();
            }
        });

    }
    public void openActivity1(){
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }
}