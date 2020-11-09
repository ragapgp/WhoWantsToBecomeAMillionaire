package com.example.whowantstobecomeamillionaire;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameLost extends AppCompatActivity {

    private static TextView status;
    private static Button bSubmit;
    //SharedPreferences sp;
    public static final String prefName = "userpref";
    SessionManager sessionManager;
    String sScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_lost);

        status = (TextView) findViewById(R.id.gameLost);
        bSubmit = (Button) findViewById(R.id.button_gotoquiz);

        sessionManager = new SessionManager(getApplicationContext());
        SharedPreferences sph = getSharedPreferences(prefName, Context.MODE_PRIVATE);
        SharedPreferences.Editor edi = sph.edit();

        final int chks = sph.getInt("sScore", 0);
        int updatedscore = sessionManager.getScore();
        System.out.println("Score is :" + updatedscore);
        status.setText("You lost the game with $" + updatedscore +" !!!");

        bSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GameLost.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}