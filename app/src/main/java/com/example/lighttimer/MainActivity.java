package com.example.lighttimer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView timerTextView;
    SeekBar timerSeekBar;
    Boolean counterIsActive = false;
    Button goButton;
    Button resetButton;
    CountDownTimer countDownTimer;
    public long totalmillis;

    public void resetTimer(){
        timerTextView.setText("00:30");
        timerSeekBar.setProgress(30);
        timerSeekBar.setEnabled(true);
        countDownTimer.cancel();
        goButton.setText("GO !");
        counterIsActive = false;
        resetButton.setVisibility(View.VISIBLE);
    }

    public void buttonClicked(View view){
        if(counterIsActive){
            pauseTimer();
        }
        else{
            startTimer();
        }
    }

    public void resetButtonClicked(View view){
        resetTimer();
    }

    public void pauseTimer(){
        countDownTimer.cancel();
        counterIsActive = false;
        goButton.setText("GO!");
        resetButton.setVisibility(View.VISIBLE);
    }

    public void updateTimer(int secondsLeft){
        int minutes = secondsLeft/60;
        int seconds = secondsLeft-(minutes*60);

        String firstString = Integer.toString(minutes);
        if(minutes <= 9){
            firstString = "0" + firstString;
        }

        String secondString = Integer.toString(seconds);
        if(seconds <= 9){
            secondString = "0" + secondString;
        }
        timerTextView.setText(firstString + ":" + secondString);
    }

    public void startTimer(){
        counterIsActive = true;
        timerSeekBar.setEnabled(false);
        goButton.setText("STOP !");
        resetButton.setVisibility(View.INVISIBLE);

        countDownTimer = new CountDownTimer(totalmillis = timerSeekBar.getProgress()*1000+100 , 1000) {
            @Override
            public void onTick(long totalmillis) {
                updateTimer((int)totalmillis/1000);
            }

            @Override
            public void onFinish() {
                resetTimer();
            }
        }.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerSeekBar = (findViewById(R.id.timerSeekBar));
        timerTextView = findViewById(R.id.countdownTextView);
        goButton = findViewById(R.id.goButton);
        resetButton = findViewById(R.id.resetButton);

        timerSeekBar.setMax(3600);
        timerSeekBar.setProgress(30);

        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                updateTimer(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}