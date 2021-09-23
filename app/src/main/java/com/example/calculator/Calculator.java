package com.example.calculator;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

import MathOperations.*;

public class Calculator implements Parcelable {

    private String expressionString;
    private Float memoryValue;
    private MathGB mathGB;

    public Calculator(){
        mathGB = new MathGB();
        expressionString = "";
        memoryValue = 0f;
    }

    public void addExpression(String inputExpressionString){
        expressionString += inputExpressionString;
    }

    public void skipMemory() {
        memoryValue = 0f;
    }

    public void setValueFromMemory() {
        expressionString = Float.toString(memoryValue);
    }

    public void putInMemory(int koef){
        memoryValue = memoryValue + getLastNumberFloat() * koef;
    }

    public void clearInput() {
        char[] expCharArr = expressionString.toCharArray();
        if (expCharArr.length > 0) {
            expressionString = String.copyValueOf(expCharArr, 0, expCharArr.length - 1);
        }
    }

    public void clearLastOperation() {
        char[] expCharArr = expressionString.toCharArray();
        int indexOperation;
        for (indexOperation = expCharArr.length - 1; indexOperation >= 0; indexOperation--) {
            char currentChar = expCharArr[indexOperation];
            if (!mathGB.charIsNumber(currentChar)) {
                expressionString = String.copyValueOf(expCharArr, 0, indexOperation + 1);
                break;
            }
        }
    }

    public void evaluate() {
        mathGB.setExpression(expressionString);
        Float result = mathGB.evaluate();
        expressionString = Float.toString(result);
    }

    private String getLastNumberString(){
        String lastNumber = "";
        char[] expCharArr = expressionString.toCharArray();
        int indexOperation;
        for (indexOperation = expCharArr.length - 1; indexOperation >= 0; indexOperation--) {
            char currentChar = expCharArr[indexOperation];
            if (mathGB.charIsNumber(currentChar)) {
                lastNumber = currentChar + lastNumber;
            }else {
                break;
            }
        }
        return lastNumber;
    }

    public boolean lastCharIsNumber(){
        char lastChar = getLastChar();
        boolean isItNumber;
        if (mathGB.charIsNumber(lastChar) && lastChar != '.'){
            isItNumber = true;
        }else {
            isItNumber = false;
        }
        return isItNumber;
    }

    private Float getLastNumberFloat(){
        Float lastNumber = 0f;
        String lastNumberString = getLastNumberString();
        lastNumber = Float.parseFloat(lastNumberString);
        return lastNumber;
    }

    private char getLastChar(){
        char[] expCharArr = expressionString.toCharArray();
        char lastChar = expCharArr[expCharArr.length - 1];
        return lastChar;
    }

    public void resetExpression(){
        expressionString = "";
    }

    public Float getMemoryValue() {
        return memoryValue;
    }

    public String getExpressionString() {
        return expressionString;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
