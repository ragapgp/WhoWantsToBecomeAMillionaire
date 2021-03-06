package com.example.whowantstobecomeamillionaire;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class QuestionFour extends AppCompatActivity {

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
    final int newScore = MainActivity.score;
    private String correctAnswer;
    private boolean answered;
    //SharedPreferences sp;
    public static final String prefName = "userpref";
    SessionManager sessionManager;
    String sScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_four);

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
        //samount = amount.getText().toString();
        bSubmit = (Button) findViewById(R.id.button_confirm);

        Bundle b = getIntent().getExtras();
        String newscore = b.getString("RIGHT_ANSWER_COUNT");
        System.out.println("Score before calculating second question is :" + newscore);

        sessionManager = new SessionManager(getApplicationContext());
        SharedPreferences sph = getSharedPreferences(prefName, Context.MODE_PRIVATE);
        SharedPreferences.Editor edi = sph.edit();

        final int chks = sph.getInt("sScore", 0);
        int updatedscore = sessionManager.getScore();
        System.out.println("Score before calculating fourth question is :" + updatedscore);
        amount.setText("You earned $ " + updatedscore);

        bSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int radioChoice = rg.getCheckedRadioButtonId();
                rbChosen = (RadioButton) findViewById(radioChoice);
                correctAnswer = "electromagnetic radiation";
                String rbSelected = rbChosen.getText().toString();
                System.out.println("Chosen answer is : " + rbSelected);
                if (radioChoice == -1) {
                    Toast.makeText(getApplicationContext(), "Please select an answer", Toast.LENGTH_SHORT).show();
                } else {
                    if (rbSelected.equals(correctAnswer)){
                        System.out.println("Score is : " + score);
                        score = 500;
                        sessionManager.setScore(score);
                        edi.putInt(sScore, score);
                        edi.commit();
                        //tQuestionNo.setText("Question " + questionCount + "/" + maxQuestionCount);
                        amount.setText("You earned $ " + score);
                        Toast.makeText(getApplicationContext(), "Correct answer! You earned $" + score, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(QuestionFour.this, QuestionFive.class);
                        intent.putExtra("RIGHT_ANSWER_COUNT", score);
                        startActivity(intent);
                        finish();
                    } else {
                        score = score + 0;
                        Toast.makeText(getApplicationContext(), "Wrong answer" , Toast.LENGTH_SHORT).show();
                        //Toast.makeText(getApplicationContext(), "You lost the game with $ " + updatedscore , Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(QuestionFour.this, GameLost.class);
                        startActivity(intent);
                        finish();
                    }
                }

            }
        });
    }
}