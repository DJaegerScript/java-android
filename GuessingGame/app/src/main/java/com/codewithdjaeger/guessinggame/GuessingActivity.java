package com.codewithdjaeger.guessinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class GuessingActivity extends AppCompatActivity {

    private int randomNumber, attempts;
    private TextView hint;
    private EditText yourGuess;
    private Intent intent;
    public static final String EXTRA_IS_WON = "com.codewithdjaeger.IS_WON";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guessing);

        this.randomNumber =  this.randomNumberGenerator();
        this.attempts = 0;

        this.intent = getIntent();
        String userName = this.intent.getStringExtra(MainActivity.EXTRA_USER_NAME);

        TextView header = findViewById(R.id.header);
        header.setText(String.format(header.getText().toString(), userName));

        this.hint = findViewById(R.id.hint);

        this.yourGuess = findViewById(R.id.your_guess);

        Button submitButton = findViewById(R.id.submit_button);
        submitButton.setOnClickListener(v -> {
            this.answerChecker(Integer.parseInt(yourGuess.getText().toString()), userName);
            this.clearLayout(v);
        });
    }

    private int randomNumberGenerator() {
        Random random = new Random();

        return random.nextInt(20) + 1;
    }

    @SuppressLint("DefaultLocale")
    private void answerChecker(int answer, String userName) {
        String hintTemplate = "%1$s than %2$d";

        this.intent = new Intent(this, GameOverActivity.class);
        this.intent.putExtra(MainActivity.EXTRA_USER_NAME, userName);

        if (this.attemptCounter() == 5) {
            this.intent.putExtra(EXTRA_IS_WON, false);
            startActivity(this.intent);
        }

        if (answer > randomNumber) {
            this.hint.setText(String.format(hintTemplate, "Lower", answer));
        }else if (answer < randomNumber){
            this.hint.setText(String.format(hintTemplate, "Higher", answer));
        }else {
            this.intent.putExtra(EXTRA_IS_WON, true);
            startActivity(this.intent);
        }

    }

    private int attemptCounter() {
        return attempts++;
    }

    private void clearLayout(View v) {
        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);

        this.yourGuess.getText().clear();
    }
}