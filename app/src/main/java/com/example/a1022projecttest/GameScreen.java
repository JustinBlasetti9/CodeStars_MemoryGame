package com.example.a1022projecttest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameScreen extends AppCompatActivity {
    private TextView gameTimer;
    private Button submitButton;
    private CountDownTimer countdown;
    private long timeLeftMS=5000; //10 mins
    private int timesPressed=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);


        gameTimer = findViewById(R.id.gameTimer);
        submitButton= (Button) findViewById(R.id.submitButton);
        //start the timer when page is loaded
        startTimer();
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitAnswer();
            }
        });


    }
    //when user submits answer, timer is restarted and shortened
    public void submitAnswer(){
        timesPressed+=1;
        timeLeftMS=5000-((long)100 *timesPressed);
        countdown.cancel();
        startTimer();
    }

    //updates the user interface on screen to reflect how much time left on the timer
    public void updateTimer(){
        int updatedSeconds = (int) timeLeftMS/1000;
        String updatedTimer="";
        updatedTimer+=updatedSeconds;
        if (updatedSeconds>=0){
            gameTimer.setText(updatedTimer);
        }

    }
    //starts the timer when this function is called
    public void startTimer(){
        countdown=new CountDownTimer(timeLeftMS, 1000){
            @Override
            public void onTick(long time){
                timeLeftMS=time;
                updateTimer();
            }
            @Override
            public void onFinish(){

            }
        }.start();
    }
}