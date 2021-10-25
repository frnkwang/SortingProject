package com.example.sortingproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int[] myList;
    private int swap1Index, swap2Index;
    private int listSize = 8;

    private Button swap1Button, swap2Button, swapButton, checkButton, newListButton;
    private TextView listTextView, swap1TextView, swap2TextView;

    public void increaseIndex1(View view) {
        swap1Index++;
        if (swap1Index == myList.length){
            swap1Index = 0;
        }
        swap1TextView.setText("First selection: " + myList[swap1Index]);
    }

    public void increaseIndex2(View view) {
        swap2Index++;
        if (swap2Index == myList.length){
            swap2Index = 0;
        }
        swap2TextView.setText("Second selection: " + myList[swap2Index]);
    }

    public void swapSelected(View view) {
        int temp = myList[swap2Index];
        myList[swap2Index] = myList[swap1Index];
        myList[swap1Index] = temp;
        temp = swap1Index;
        swap1Index = swap2Index;
        swap2Index = temp;

        updateListDisplay();
    }

    public void createNewList(View view) {
        myList = new int[listSize];
        for (int i = 1; i <= listSize; i++){
            myList[i-1] = i;
        }
        shuffleList();
        updateListDisplay();
    }

    public void checkAnswer(View view) {
        Intent intent = new Intent(MainActivity.this, resultsScreen.class);
        String message;
        if (isOrdered()){
            message = "Congrats! You sorted a list";
        }
        else{
            message = "Sorry, that wasn't in order.";
        }
        intent.putExtra("MESSAGE", message);
        startActivity(intent);
    }

    private void shuffleList() {
        int swaps = (int)(Math.random()*10) + 20;
        for (int i = 0; i < swaps; i++) {
            int j = (int)(Math.random() * myList.length);
            int k = (int)(Math.random() * myList.length);
            int temp = myList[j];
            myList[j] = myList[k];
            myList[k] = temp;
        }
    }

    private boolean isOrdered() {
        for (int i = 0; i < myList.length-1; i++){
            if (myList[i] > myList[i+1]){
                return false;
            }
        }
        return true;
    }

    private void updateListDisplay() {
        String toDisplay = "";
        for (int i : myList){
            toDisplay += i;
            toDisplay += "\t";
        }
        listTextView.setText(toDisplay.trim());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swap1Button = findViewById(R.id.swap1Button);
        swap2Button = findViewById(R.id.swap2Button);
        swapButton = findViewById(R.id.swapButton);
        checkButton = findViewById(R.id.checkButton);
        newListButton = findViewById(R.id.newListButton);
        listTextView = findViewById(R.id.listTextView);
        swap1TextView = findViewById(R.id.swap1TextView);
        swap2TextView = findViewById(R.id.swap2TextView);

        createNewList(this.getCurrentFocus());
        updateListDisplay();

        swap1Index = 0;
        swap2Index = 1;
        swap1TextView.setText("First selection: "+myList[swap1Index]);
        swap2TextView.setText("Second selection: "+myList[swap2Index]);
    }
}