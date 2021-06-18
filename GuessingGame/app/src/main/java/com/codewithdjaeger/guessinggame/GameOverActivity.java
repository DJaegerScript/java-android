package com.codewithdjaeger.guessinggame;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GameOverActivity extends AppCompatActivity {

    Intent intent;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        this.intent = getIntent();

        boolean isWon = this.intent.getBooleanExtra(GuessingActivity.EXTRA_IS_WON, false);

        TextView resultMessage = findViewById(R.id.result_message), result = findViewById(R.id.result);

        final String winner =  "You %1$s";
        String userName = this.intent.getStringExtra(MainActivity.EXTRA_USER_NAME);

        String resultMessageValue = isWon ? "Congratulation, " + userName : "Game Over";
        String resultValue = isWon ? "Win" : "Lose";

        resultMessage.setText(resultMessageValue);
        result.setText(String.format(winner, resultValue));

        Button exitButton = findViewById(R.id.exit_button);
        Button playAgainButton = findViewById(R.id.play_again_button);

        exitButton.setOnClickListener(v -> this.finishAffinity());
        playAgainButton.setOnClickListener(v -> this.playAgain(userName) );
    }

    private void playAgain(String userName) {
        this.intent = new Intent(this, GuessingActivity.class);
        this.intent.putExtra(MainActivity.EXTRA_USER_NAME, userName);
        startActivity(this.intent);
    }
}