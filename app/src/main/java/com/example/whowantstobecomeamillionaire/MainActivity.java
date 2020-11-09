package com.example.whowantstobecomeamillionaire;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
/* Main Activity class which defines first quiz question and saves score in shared preferences */
public class MainActivity extends AppCompatActivity {

    // Mention the duration splash screen will reload
    private static int SPLASH_TIME = 450000000;


    // Declare quiz variables
    public static int score = 0;
    public static int questionCount = 1;
    static final private int maxQuestionCount = 5;
    private static RadioGroup rg;
    private static RadioButton rb1, rb2, rb3, rb4;
    private static String srb1, srb2, srb3, srb4, samount;
    private static Button bSubmit;
    private static RadioButton rbChosen;
    private static TextView amount;
    private static TextView tQuestionNo;
    private String correctAnswer;
    private boolean answered;

    //SharedPreferences sp;
    public static final String prefName = "userpref";
    SessionManager sessionManager;
    String sScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, SplashActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIME);

        sessionManager = new SessionManager(getApplicationContext());
        SharedPreferences sph = getSharedPreferences(prefName, Context.MODE_PRIVATE);
        SharedPreferences.Editor edi = sph.edit();

        rg = (RadioGroup) findViewById(R.id.radio_group);
        rb1 = (RadioButton) findViewById(R.id.radio_button1);
        rb2 = (RadioButton) findViewById(R.id.radio_button2);
        rb3 = (RadioButton) findViewById(R.id.radio_button3);
        rb4 = (RadioButton) findViewById(R.id.radio_button4);
        amount = (TextView) findViewById(R.id.earnAmount);
        srb1 = rb1.getText().toString();
        srb2 = rb2.getText().toString();
        srb3 = rb3.getText().toString();
        srb4 = rb4.getText().toString();
        samount = amount.getText().toString();
        bSubmit = (Button) findViewById(R.id.button_confirm);

        sph = getSharedPreferences(prefName, MODE_PRIVATE);
        score = sph.getInt("score", 0);

        //amount.setText("You earned $ " + score );

        bSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int radioChoice = rg.getCheckedRadioButtonId();
                rbChosen = (RadioButton) findViewById(radioChoice);
                correctAnswer = "Canberra";
                    if (radioChoice == -1) {
                        Toast.makeText(getApplicationContext(), "Please select an answer", Toast.LENGTH_SHORT).show();
                    } else {
                            String rbSelected = rbChosen.getText().toString();
                            System.out.println("Chosen answer is : " + rbSelected);
                            if (rbSelected.equals(correctAnswer)){
                                score = 100;
                                sessionManager.setScore(score);
                                edi.putInt(sScore, score);
                                edi.commit();
                                System.out.println("Score in 1st question is :" + score);
                                amount.setText("You earned $ " + score);
                                Toast.makeText(getApplicationContext(), "Correct answer! You earned $" + score, Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(MainActivity.this, QuestionTwo.class);
                                intent.putExtra("RIGHT_ANSWER_COUNT", score);
                                startActivity(intent);
                                finish();
                            } else {
                                //score = score + 0;
                                System.out.println("Score in 1st question is :" + score);
                                amount.setText("You earned " + score + " $");
                                Toast.makeText(getApplicationContext(), "Wrong answer" , Toast.LENGTH_SHORT).show();
                                //Toast.makeText(getApplicationContext(), "You lost the game with $ " + score , Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(MainActivity.this, GameLost.class);
                                //intent.putExtras(getIntent().getExtras());
                                intent.putExtra("RIGHT_ANSWER_COUNT", score);
                                startActivity(intent);
                                finish();
                            }
                }

            }
        });
    }
}