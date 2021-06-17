package com.codewithdjaeger.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mainText;
    private EditText yourText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mainText = findViewById(R.id.main_text);
        this.yourText = findViewById(R.id.your_text);

        Button textChanger = findViewById(R.id.text_changer);

        textChanger.setOnClickListener(v -> this.changeText());
    }

    private void changeText() {
        String yourTextValue = this.yourText.getText().toString();
        this.mainText.setText(yourTextValue);
        this.yourText.getText().clear();
    }
}