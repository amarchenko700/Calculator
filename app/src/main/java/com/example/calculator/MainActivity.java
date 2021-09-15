package com.example.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String OBJECT_CALCULATOR = "OBJECT_CALCULATOR";
    private TextView expression_string_textview, text_memory_textview;
    private Calculator calculator;

    private final int[] arrayNumberButton = new int[]{R.id.number_0_button, R.id.number_1_button,
            R.id.number_2_button, R.id.number_3_button, R.id.number_4_button, R.id.number_5_button,
            R.id.number_6_button, R.id.number_7_button, R.id.number_8_button, R.id.number_9_button};

    private final int[] numberOperationIds = new int[]{R.id.addition_button, R.id.subtraction_button,
            R.id.multiplication_button, R.id.division_button};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        expression_string_textview = findViewById(R.id.expression_string_textview);
        text_memory_textview = findViewById(R.id.text_memory_textview);

        if (savedInstanceState == null) {
            calculator = new Calculator();
        } else {
            calculator = savedInstanceState.getParcelable(OBJECT_CALCULATOR);
            expression_string_textview.setText(calculator.getExpressionString());
        }

        if (calculator.getMemoryValue() == 0) {
            text_memory_textview.setVisibility(View.INVISIBLE);
        } else {
            text_memory_textview.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        outState.putParcelable(OBJECT_CALCULATOR, calculator);
        super.onSaveInstanceState(outState, outPersistentState);
    }
}