package com.example.a1022projecttest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Random;


public class GameScreen extends AppCompatActivity {

    private TextView gameTimer;
    private Button submitButton, backToMainButton, saveScoreButton;
    private CountDownTimer countdown;
    private long timeLeftMS=5000; //5 mins
    private int timesPressed=0;
    private TextView generatedNumber, displayedRound, currentScore;
    private EditText userGuess;
    private int currentRound= 1;
    private String theCode="";
    private String mode = "";
    private int score = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        //receive the passed intent data from the ModeSelect class
        Bundle extras = getIntent().getExtras();
        this.mode = extras.getString("modeSelected");

        //set the previously defined variables to their respective text/edit/button views by ID
        gameTimer = findViewById(R.id.gameTimer);
        submitButton = (Button) findViewById(R.id.submitButton);
        backToMainButton = (Button) findViewById(R.id.gameOverButton);
        saveScoreButton = (Button) findViewById(R.id.saveScore);
        generatedNumber = findViewById(R.id.generatedNumber);
        userGuess = findViewById(R.id.userGuess);
        displayedRound = findViewById(R.id.gameRound);
        currentScore=findViewById(R.id.currentScore);

        //create the first round code based on normal or hard mode
        if (this.mode.equals("normal")){
            addDigit();
        }
        else{
            addLetter();
        }

        //hide the textbox, submit button and the return to main menu button when page first loads
        userGuess.setVisibility(View.INVISIBLE);
        submitButton.setVisibility(View.INVISIBLE);
        backToMainButton.setVisibility(View.INVISIBLE);
        saveScoreButton.setVisibility(View.INVISIBLE);

        //set the textview of the code to the generated code
        generatedNumber.setText(this.theCode);

        //display the current round
        displayedRound.setText("Round "+currentRound);

        //start the timer when page is loaded
        startTimer();
        backToMainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMainMenu();

            }
        });

        saveScoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLeaderBoard();
            }
        });

        //create an on click listener for the submit button that calls the submitAnswer() function every time it is clicked
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitAnswer();
            }
        });


    }
    //when user submits answer, timer is restarted and shortened
    public void submitAnswer(){
        //user gave correct answer
        if ((this.theCode.toLowerCase()).equals(userGuess.getText().toString().toLowerCase())){
            //clear the textbox for next round
            userGuess.setText("");
            //decrease the time given to remember the code every round
            timesPressed+=1;
            timeLeftMS=5000-((long)100 *timesPressed);
            countdown.cancel();
            startTimer();
            //increment the round by 1
            this.currentRound+=1;
            //display the current round after the increment
            displayedRound.setText("Round "+currentRound);
            //if the selected mode from ModeSelect is normal, add a number, otherwise add a letter to the code
            if (this.mode.equals("normal")){
                addDigit();
                this.score+=50;

            }
            else{
                addLetter();
                this.score+=100;

            }
            currentScore.setText("Score: "+this.score);

            //display the current code after adding
            generatedNumber.setText(this.theCode);
            userGuess.setVisibility(View.INVISIBLE);
            submitButton.setVisibility(View.INVISIBLE);
        }
        //user gave a wrong answer
        else {
            gameOver();
        }
    }

    //updates the user interface on screen to reflect how much time left on the timer
    public void updateTimer(){
        int updatedSeconds = (int) timeLeftMS/1000;
        String updatedTimer="Time Left to Memorize: ";
        updatedTimer+=updatedSeconds;
        if (updatedSeconds>=0){
            gameTimer.setText(updatedTimer);
        }
        gameTimer.setVisibility(View.VISIBLE);

    }
    //starts the timer when this function is called
    public void startTimer(){
        countdown=new CountDownTimer(timeLeftMS, 1000){
            @Override
            public void onTick(long time){
                //update time every tick and then update the displayed time
                timeLeftMS=time;
                updateTimer();
            }
            @Override
            public void onFinish(){
                //when timer runs out, call timesUp() function
                timesUp();
            }
        }.start();
    }
    public void addDigit(){
        //uses the random library to generate a random number from 0 to 9 and then adds it to the code
        Random randomDigit = new Random();
        int digit= randomDigit.nextInt(10);
        this.theCode+=digit;
    }

    public void gameOver(){
        //set submit button, current round, and the textbox to be invisible and display a game over message with a return to main menu button
        displayedRound.setVisibility(View.INVISIBLE);

        submitButton.setVisibility(View.INVISIBLE);
        userGuess.setVisibility(View.INVISIBLE);
        generatedNumber.setText("Game Over!\n\n\nYour Answer: "+userGuess.getText().toString()+"\n\nActual Code: "+ this.theCode);
        backToMainButton.setVisibility(View.VISIBLE);
        saveScoreButton.setVisibility(View.VISIBLE);
    }

    public void timesUp(){
        //set the code to be invisible and prompt the user to give their guess
        userGuess.setVisibility(View.VISIBLE);
        gameTimer.setVisibility(View.INVISIBLE);
        submitButton.setVisibility(View.VISIBLE);
        generatedNumber.setText("What was the code?");
    }

    public void openMainMenu(){
        //returns to the main menu screen
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }

    public void addLetter(){
        //sets a range from min to max that will be assigned ASCII values randomly, effectively creating a game code consisting of letters and numbers
        int min;
        int max;
        if (Math.random()<0.5) {
            min = 65;
            max = 90;
        }
        else{
            min=48;
            max=57;
        }
        int randomNum = (int)Math.floor(Math.random()*(max-min+1)+min);
        //convert the ASCII values to their respective characters (0 to 9 or A to z) and add it to the game code
        this.theCode+=(char) randomNum;
    }


    public void openLeaderBoard(){
        //returns to the main menu screen
        Intent intent = new Intent(this, Leaderboard.class);
        intent.putExtra("scoreToBeSaved", this.score);
        startActivity(intent);
    }
}