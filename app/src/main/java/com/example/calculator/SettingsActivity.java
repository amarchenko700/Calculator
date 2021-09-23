package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

public class SettingsActivity extends AppCompatActivity {

    private int themeIndex = 0;

    private final int[] radioBtns = new int[]{R.id.radio_button_default, R.id.radio_button_summer};

    View.OnClickListener radioButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.radio_button_default: {
                    themeIndex = 0;
                    break;
                }
                case R.id.radio_button_summer: {
                    themeIndex = 1;
                    break;
                }
            }
        }
    };

    private void setupClickListenersChooseThemeRadioButton(){

        for (int radioBtn: radioBtns){
            RadioButton rb = findViewById(radioBtn);
            rb.setOnClickListener(radioButtonListener);
            rb.setChecked(false);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        setupClickListenersChooseThemeRadioButton();

        themeIndex = getIntent().getIntExtra(MainActivity.THEME_INDEX, themeIndex);
        if(themeIndex == 0){
            RadioButton rb = findViewById(R.id.radio_button_default);
            rb.setChecked(true);
        }else if(themeIndex == 1){
            RadioButton rb =  findViewById(R.id.radio_button_summer);
            rb.setChecked(true);
        }

        findViewById(R.id.choose_text_button).setOnClickListener(v -> {
            Intent resultIntent = new Intent();
            resultIntent.putExtra(MainActivity.THEME_INDEX, themeIndex);
            setResult(Activity.RESULT_OK, resultIntent);
            finish();
        });
    }
}