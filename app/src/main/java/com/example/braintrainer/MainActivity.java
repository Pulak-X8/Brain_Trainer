package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button option1;
    Button option2;
    Button option3;
    Button option4;
    Button reset;
    TextView sum;
    TextView score;
    TextView answer;
    TextView time;
    int[] choices = {0,0,0,0};
    int correctAnswerButton, points = 0, q = 0, ans = 0;

    public void generate(){
        Random r  = new Random();
        int a = r.nextInt(50), b = r.nextInt(50), s = r.nextInt(5);
        String op = "";
        switch (s){
            case(0):{
                ans = a + b;
                op = " + ";
                break;
            } case(1):{
                while (a <= b){
                    b = r.nextInt(a) + 1;
                }
                ans = a - b;
                op = " - ";
                break;
            } case(2):{
                ans = a * b;
                op = " * ";
                break;
            } case(3):{
                while (a <= b){
                    b = r.nextInt(a) + 1;
                }
                ans = a / b;
                op = " * ";
                break;
            } case(4):{
                while (a <= b){
                    b = r.nextInt(a) + 1;
                }
                ans = a % b;
                op = " % ";
                break;
            }
        }
        sum.setText(a + op + b);
        correctAnswerButton = r.nextInt(4);
        for (int i=0;i<4;i++){
            if (i == correctAnswerButton){
                choices[i] = ans;
            }else{
                while (choices[i] == ans) {
                    choices[i] = r.nextInt(20) + ans - 10;
                }
            }
        }
        option1.setText(Integer.toString(choices[0]));
        option2.setText(Integer.toString(choices[1]));
        option3.setText(Integer.toString(choices[2]));
        option4.setText(Integer.toString(choices[3]));
    }

    public void options(View view){
        if (view.getTag().toString().equals(Integer.toString(correctAnswerButton))){
            points++;
            answer.setText("Correct");
        }else{
            answer.setText("Wrong");
        }
        q++;
        score.setText(points+"/"+q);
        generate();
    }

    public void playAgain(View view){
        int points = 0, q = 0;
        score.setText(points+"/"+q);
        answer.setText("Start");
        score.setTextColor(android.graphics.Color.rgb(255, 255, 255));
        option1.setEnabled(true);
        option2.setEnabled(true);;
        option3.setEnabled(true);;
        option4.setEnabled(true);;
        new CountDownTimer(300200, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                time.setText((int) millisUntilFinished / 1000 + "s");
                if ((int) millisUntilFinished/1000 == 5){
                    time.setTextColor(android.graphics.Color.rgb(255, 0, 0));
                }
            }

            @Override
            public void onFinish() {
                time.setText("0s");
                answer.setText("Time's up");
                option1.setEnabled(false);
                option2.setEnabled(false);
                option3.setEnabled(false);
                option4.setEnabled(false);
                reset.setVisibility(View.VISIBLE);
            }
        }.start();
        generate();
        reset.setVisibility(View.INVISIBLE);
    }

    public void go(View view){
        Button start = findViewById(R.id.button);
        ConstraintLayout gameLayout = findViewById(R.id.gameLayout);
        start.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        playAgain(view);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        option1 = findViewById(R.id.button1);
        option2 = findViewById(R.id.button2);
        option3 = findViewById(R.id.button3);
        option4 = findViewById(R.id.button4);
        reset = findViewById(R.id.reset);
        sum = findViewById(R.id.sum);
        score = findViewById(R.id.score);
        time = findViewById(R.id.time);
        answer = findViewById(R.id.answer);
    }
}