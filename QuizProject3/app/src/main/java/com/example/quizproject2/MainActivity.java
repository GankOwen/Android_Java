package com.example.quizproject2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView question1TextView;
    TextView question2TextView;
    TextView question3TextView;
    TextView question4TextView;
    RadioGroup question1RadioGroup;
    RadioGroup question2RadioGroup;
    LinearLayout question3CheckBoxGroup;
    EditText question4EditText;
    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        question1TextView = findViewById(R.id.main_question1_textView);
        question2TextView = findViewById(R.id.main_question2_textView);
        question3TextView = findViewById(R.id.main_question3_textView);
        question4TextView = findViewById(R.id.main_question4_textView);
        question1RadioGroup = findViewById(R.id.main_question1_radioGroup);
        question2RadioGroup = findViewById(R.id.main_question2_radioGroup);
        question3CheckBoxGroup = findViewById(R.id.main_question3_checkBoxGroup);
        question4EditText = findViewById(R.id.main_question4_editText);
        submitButton = findViewById(R.id.main_submit_button);


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int numberOfQuestionGotWrong = 0;
                int totalScore = 0;
                if (((RadioButton) question1RadioGroup.getChildAt(1)).isChecked()) {
                    totalScore+=25;
                }else {
                    numberOfQuestionGotWrong++;
                }

                if (((RadioButton) question2RadioGroup.getChildAt(2)).isChecked()) {
                    totalScore+=25;
                }else{
                    numberOfQuestionGotWrong++;
                }

                if (((CheckBox) question3CheckBoxGroup.getChildAt(1)).isChecked() && ((CheckBox) question3CheckBoxGroup.getChildAt(3)).isChecked()
                        && !((CheckBox) question3CheckBoxGroup.getChildAt(0)).isChecked() && !((CheckBox) question3CheckBoxGroup.getChildAt(2)).isChecked()) {
                    totalScore+=25;
                }else{
                    numberOfQuestionGotWrong++;
                }
                if (question4EditText.getText().toString().replaceAll(" ", "").equalsIgnoreCase("n")) {
                    totalScore+=25;
                }else{
                    numberOfQuestionGotWrong++;
                }
                Toast.makeText(getBaseContext(), "Your score is " + totalScore + ", your got " + numberOfQuestionGotWrong + " wrong", Toast.LENGTH_SHORT).show();
                numberOfQuestionGotWrong = 0;
                totalScore = 0;
            }
        });
    }
}