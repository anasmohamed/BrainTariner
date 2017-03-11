package com.example.android.braintrainer;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends Activity {
    private Button goBtn, playAgain, btn1, btn2, btn3, btn4;
    private TextView seconds, score, questions, result;
    private GridLayout mBoard;
    ArrayList<Integer> answers = new ArrayList();
    Random randomBtn;
    int locationOfCorrectAnswer;
    int counter;
    int numberOfQuestions = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        goBtn = (Button) findViewById(R.id.goBtn);
        btn1 = (Button) findViewById(R.id.answer1);
        btn2 = (Button) findViewById(R.id.answer2);
        btn3 = (Button) findViewById(R.id.answer3);
        btn4 = (Button) findViewById(R.id.answer4);
        playAgain = (Button) findViewById(R.id.play_again);
        score = (TextView) findViewById(R.id.score);
        result = (TextView) findViewById(R.id.result);
        seconds = (TextView) findViewById(R.id.second);
        questions = (TextView) findViewById(R.id.questions);
        mBoard = (GridLayout) findViewById(R.id.board);
        counter = 0;
        generateRandomNumbers();


    }

    public void start(View view) {
        goBtn.setVisibility(view.INVISIBLE);
        seconds.setVisibility(View.VISIBLE);
        score.setVisibility(View.VISIBLE);
        mBoard.setVisibility(View.VISIBLE);
        questions.setVisibility(view.VISIBLE);
        countDown(view);

    }

    public void generateRandomNumbers() {
        randomBtn = new Random();
        int a = randomBtn.nextInt(21);
        int b = randomBtn.nextInt(21);
        answers.clear();
        int incorrectAnswers;
        questions.setText(String.valueOf(a) + " + " + String.valueOf(b));
        locationOfCorrectAnswer = randomBtn.nextInt(4);

        for (int i = 0; i < 4; i++) {
            if (i == locationOfCorrectAnswer) {
                answers.add(a + b);
            } else {
                incorrectAnswers = randomBtn.nextInt(41);
                while (incorrectAnswers == a + b) {
                    incorrectAnswers = randomBtn.nextInt(41);
                }
                answers.add(incorrectAnswers);
            }
        }
        btn1.setText(Integer.toString(answers.get(0)));
        btn2.setText(Integer.toString(answers.get(1)));
        btn3.setText(Integer.toString(answers.get(2)));
        btn4.setText(Integer.toString(answers.get(3)));
    }

    public void correctAnswer(View view) {

        result.setVisibility(view.VISIBLE);
        if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))) {
            counter++;
            result.setText("Correct!");
        } else {
            result.setText("Wrong");

        }
        numberOfQuestions++;
        score.setText(Integer.toString(counter) + "/" + Integer.toString(numberOfQuestions));
        generateRandomNumbers();

    }

    public void playAgain(View view) {
        counter = 0;
        numberOfQuestions = 0;
        result.setText("");
        score.setText("0/0");
        seconds.setText("30s");
        playAgain.setVisibility(view.INVISIBLE);
        generateRandomNumbers();
        btn1.setEnabled(true);
        btn2.setEnabled(true);
        btn3.setEnabled(true);
        btn4.setEnabled(true);
        countDown(view);
    }
    public void countDown(final View view) {
        new CountDownTimer(30100, 1000) {
            @Override
            public void onTick(long l) {
                seconds.setText(Integer.toString((int) (l / 1000)) + "s");
            }

            @Override
            public void onFinish() {
                seconds.setText("0s");
                result.setText("Your Score " + Integer.toString(counter) + "/" + Integer.toString(numberOfQuestions));
                btn1.setEnabled(false);
                btn2.setEnabled(false);
                btn3.setEnabled(false);
                btn4.setEnabled(false);

                playAgain.setVisibility(view.VISIBLE);
            }
        }.start();
    }

}
