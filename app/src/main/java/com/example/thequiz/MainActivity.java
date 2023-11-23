package com.example.thequiz;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView no_of_question;
    TextView questions;
    Button ans_A,ans_B,ans_C,ans_D;
    Button submit_btn;

    int score=0;
    int total_Question= QuestionAnswer.questions.length;
    int current_questionIndex=0;
    String selectedAns;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        no_of_question=findViewById(R.id.no_of_question);
        questions=findViewById(R.id.Question_section);
        ans_A=findViewById(R.id.button_A);
        ans_B=findViewById(R.id.button_B);
        ans_C=findViewById(R.id.button_C);
        ans_D=findViewById(R.id.button_D);
        submit_btn = findViewById(R.id.submit_btn);

        ans_A.setOnClickListener(this);
        ans_B.setOnClickListener(this);
        ans_C.setOnClickListener(this);
        ans_D.setOnClickListener(this);
        submit_btn.setOnClickListener(this);

        no_of_question.setText("Total Question"+total_Question);

        loadNewQuestion();

    }

    @Override
    public void onClick(View view) {


            ans_A.setBackgroundColor(Color.GRAY);
            ans_B.setBackgroundColor(Color.GRAY);
            ans_C.setBackgroundColor(Color.GRAY);
            ans_D.setBackgroundColor(Color.GRAY);

            Button clickedBtn = (Button) view;
            if(clickedBtn.getId()==R.id.submit_btn){

            if(selectedAns.equals(QuestionAnswer.correctAns[current_questionIndex])){
                score++;
            }
                current_questionIndex++;
                loadNewQuestion();

            }
            else {
                selectedAns=clickedBtn.getText().toString();
                clickedBtn.setBackgroundColor(Color.BLUE);
            }
    }
   void loadNewQuestion(){
        if(current_questionIndex==total_Question){
            finishQuiz();
            return;

        }
    questions.setText(QuestionAnswer.questions[current_questionIndex]);
    ans_A.setText(QuestionAnswer.options[current_questionIndex][0]);
       ans_B.setText(QuestionAnswer.options[current_questionIndex][1]);
       ans_C.setText(QuestionAnswer.options[current_questionIndex][2]);
       ans_D.setText(QuestionAnswer.options[current_questionIndex][3]);
    }
    void finishQuiz(){
        String passStatus;
        if(score>total_Question*0.60){
            passStatus="PASS";
        }
        else {
            passStatus="FAILED";
        }
        new AlertDialog.Builder(this).setTitle(passStatus)
                .setMessage("score is:"+score+"out of"+total_Question)
                .setPositiveButton("Restart",(dialogInterface, i) ->restartquiz())
                .setCancelable(false).show();
    }
    void restartquiz() {
        score = 0;
        current_questionIndex = 0;
        loadNewQuestion();
    }
}