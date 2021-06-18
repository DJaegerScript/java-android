package com.codewithdjaeger.guessinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText nameField;
    public static final String EXTRA_USER_NAME = "com.codewithdjaeger.USER_NAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameField = findViewById(R.id.name_field);

        Button nextButton = findViewById(R.id.next_button);

        nextButton.setOnClickListener(v -> nextScreen());
    }

    private void nextScreen() {
        Intent intent =  new Intent(this, GuessingActivity.class);

        intent.putExtra(EXTRA_USER_NAME, nameField.getText().toString());
        startActivity(intent);
    }
}