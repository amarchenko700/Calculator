package com.example.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String OBJECT_CALCULATOR = "OBJECT_CALCULATOR";
    private TextView expression_string_textview, text_memory_textview;
    private Calculator calculator;

    private final int[] arrayCalculatorButtonId = new int[]{R.id.number_0_button, R.id.number_1_button,
            R.id.number_2_button, R.id.number_3_button, R.id.number_4_button, R.id.number_5_button,
            R.id.number_6_button, R.id.number_7_button, R.id.number_8_button, R.id.number_9_button};

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

        setVisibleTextMemory();
        setClickListeners(calculator);
    }

    private void setVisibleTextMemory() {
        if (calculator.getMemoryValue() == 0) {
            text_memory_textview.setVisibility(View.INVISIBLE);
        } else {
            text_memory_textview.setVisibility(View.VISIBLE);
        }
    }

    //region Установка_Listener
    private void setClickListeners(Calculator calculator) {

        CalcButtonListener calcButtonListener = new CalcButtonListener(calculator);

        setupClickListenersMemoryButtons(calcButtonListener);
        setupClickListenersFunctionButtons(calcButtonListener);
        setupClickListenersNumberButtons(calcButtonListener);

    }

    private void setupClickListenersMemoryButtons(CalcButtonListener calcButtonListener){
        setClickListenerOnButton(R.id.memory_clear_button, calcButtonListener);
        setClickListenerOnButton(R.id.memory_read_button, calcButtonListener);
        setClickListenerOnButton(R.id.memory_addition_button, calcButtonListener);
        setClickListenerOnButton(R.id.memory_subtraction_button, calcButtonListener);
    }

    private void setupClickListenersFunctionButtons(CalcButtonListener calcButtonListener){
        setClickListenerOnButton(R.id.clear_entry_button, calcButtonListener);
        setClickListenerOnButton(R.id.clear_button, calcButtonListener);
        setClickListenerOnButton(R.id.delete_button, calcButtonListener);
        setClickListenerOnButton(R.id.equally_button, calcButtonListener);
        setClickListenerOnButton(R.id.dot_button, calcButtonListener);
        setClickListenerOnButton(R.id.addition_button, calcButtonListener);
        setClickListenerOnButton(R.id.subtraction_button, calcButtonListener);
        setClickListenerOnButton(R.id.multiplication_button, calcButtonListener);
        setClickListenerOnButton(R.id.division_button, calcButtonListener);
    }

    private void setupClickListenersNumberButtons(CalcButtonListener calcButtonListener){
        for (int numberOperationId : arrayCalculatorButtonId) {
            findViewById(numberOperationId).setOnClickListener(v -> {
                Button calculatorButton = (Button) v;
                calculator.addExpression(calculatorButton.getText().toString());
                expression_string_textview.setText(calculator.getExpressionString());
            });
        }
    }

    private void setClickListenerOnButton(int idView, CalcButtonListener calcButtonListener) {
        if (findViewById(idView) != null)
            findViewById(idView).setOnClickListener(calcButtonListener);
    }

    class CalcButtonListener implements View.OnClickListener {

        private Calculator calculator;

        public CalcButtonListener(Calculator calculator) {
            this.calculator = calculator;
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                //region memory
                case R.id.memory_clear_button: {
                    calculator.skipMemory();
                    setVisibleTextMemory();
                    break;
                }
                case R.id.memory_read_button: {
                    calculator.setValueFromMemory();
                    break;
                }
                case R.id.memory_addition_button: {
                    calculator.putInMemory(1);
                    setVisibleTextMemory();
                    break;
                }
                case R.id.memory_subtraction_button: {
                    calculator.putInMemory(-1);
                    setVisibleTextMemory();
                    break;
                }
                //endregion
                case R.id.clear_button: {
                    calculator.resetExpression();
                    break;

                }
                case R.id.clear_entry_button: {
                    calculator.clearLastOperation();
                    break;
                }
                case R.id.delete_button: {
                    calculator.clearInput();
                    break;
                }
                case R.id.equally_button: {
                    calculator.evaluate();
                    break;
                }
                case R.id.dot_button: {
                    addExpressionIfLastCharNumber(".");
                    break;
                }
                case R.id.addition_button:{
                    addExpressionIfLastCharNumber("+");
                    break;
                }
                case R.id.subtraction_button:{
                    addExpressionIfLastCharNumber("-");
                    break;
                }
                case R.id.multiplication_button:{
                    addExpressionIfLastCharNumber("*");
                    break;
                }
                case R.id.division_button:{
                    addExpressionIfLastCharNumber("/");
                    break;
                }
                default: {
                    break;
                }
            }
            expression_string_textview.setText(calculator.getExpressionString());
        }
    }
    //endregion

    private void addExpressionIfLastCharNumber(String expression){
        if (calculator.lastCharIsNumber()){
            calculator.addExpression(expression);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(OBJECT_CALCULATOR, calculator);
    }
}