package com.example.tabletenniscounterproject2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    RadioGroup theRadioGroup;
    Button addButton1;
    Button addButton2;
    Button minusButton1;
    Button minusButton2;
    Button foulButton1;
    Button foulButton2;
    Button resetButton;
    TextView goal1;
    TextView goal2;
    TextView winnerTextView;
    RadioButton currentRadioButton;
    int player1Score = 0;
    int player2Score = 0;
    int winScore = 11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        theRadioGroup = findViewById(R.id.main_radioGroup);
        addButton1 = findViewById(R.id.main_addPoint1_button);
        addButton2 = findViewById(R.id.main_addPoint2_button);
        minusButton1 = findViewById(R.id.main_minusPoint1_button);
        minusButton2 = findViewById(R.id.main_minusPoint2_button);
        goal1 = findViewById(R.id.main_goal1_textView);
        goal2 = findViewById(R.id.main_goal2_textView);
        winnerTextView = findViewById(R.id.main_final_winner_textView);
        currentRadioButton = findViewById(R.id.main_single_radioButton);
        foulButton1 = findViewById(R.id.main_foul1_button);
        foulButton2 = findViewById(R.id.main_foul2_button);
        resetButton = findViewById(R.id.main_reset_button);

        theRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkId) {
                currentRadioButton = findViewById(checkId);
                Toast.makeText(radioGroup.getContext(),currentRadioButton.getText().toString()
                        +" has been selected", Toast.LENGTH_SHORT).show();
            }
        });

        addButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goal1.setText(String.valueOf(++player1Score));
                if(player1Score==winScore){
                    winnerTextView.setText("Player1 won in the "+currentRadioButton.getText()
                            +" game with score "+player1Score+" : "+player2Score);
                    addButton1.setEnabled(false);
                    addButton2.setEnabled(false);
                    minusButton1.setEnabled(false);
                    minusButton2.setEnabled(false);
                    foulButton1.setEnabled(false);
                    foulButton2.setEnabled(false);
                }
                if((player1Score>=10&&player2Score>=10)&&(player1Score==player2Score)){
                    winScore++;
                }
            }
        });

        addButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goal2.setText(String.valueOf(++player2Score));
                if(player2Score==winScore){
                    winnerTextView.setText("Player2 won in the "+currentRadioButton.getText()
                            +" game with score "+player1Score+" : "+player2Score);
                    addButton1.setEnabled(false);
                    addButton2.setEnabled(false);
                    minusButton1.setEnabled(false);
                    minusButton2.setEnabled(false);
                    foulButton1.setEnabled(false);
                    foulButton2.setEnabled(false);
                }
                if((player1Score>=10&&player2Score>=10)&&(player1Score==player2Score)){
                    winScore++;
                }
            }
        });

        minusButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(player1Score>0){
                    goal1.setText(Integer.toString(--player1Score));
                }
            }
        });

        minusButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(player2Score>0){
                    goal2.setText(Integer.toString(--player2Score));
                }
            }
        });

        foulButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goal2.setText(String.valueOf(++player2Score));
            }
        });

        foulButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goal1.setText(String.valueOf(++player1Score));
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addButton1.setEnabled(true);
                addButton2.setEnabled(true);
                minusButton1.setEnabled(true);
                minusButton2.setEnabled(true);
                foulButton1.setEnabled(true);
                foulButton2.setEnabled(true);
                player1Score = 0;
                player2Score = 0;
                winScore = 11;
                winnerTextView.setText("");
                goal1.setText(String.valueOf(player1Score));
                goal2.setText(String.valueOf(player2Score));
            }
        });
    }
}