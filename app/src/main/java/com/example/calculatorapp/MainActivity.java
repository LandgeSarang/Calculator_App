package com.example.calculatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView resultTv,solutionTv;
    MaterialButton buttonC,buttonBrackOpen,buttonBrackClose;
    MaterialButton buttonDivide,buttonMultiply,buttonPlus,buttonMinus,buttonEquals;
    MaterialButton button0,button1,button2,button3,button4,button5,button6,button7,button8,button9;
    MaterialButton buttonAC,buttonDot;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTv = findViewById(R.id.result_tv);
        solutionTv = findViewById(R.id.solution_tv);

        assign(buttonC,R.id.button_c);
        assign(buttonBrackOpen,R.id.button_open_bracket);
        assign(buttonBrackClose,R.id.button_close_bracket);
        assign(buttonDivide,R.id.button_divide);
        assign(buttonMultiply,R.id.button_multiply);
        assign(buttonPlus,R.id.button_plus);
        assign(buttonMinus,R.id.button_minus);
        assign(buttonEquals,R.id.button_equals);
        assign(button0,R.id.button_0);
        assign(button1,R.id.button_1);
        assign(button2,R.id.button_2);
        assign(button3,R.id.button_3);
        assign(button4,R.id.button_4);
        assign(button5,R.id.button_5);
        assign(button6,R.id.button_6);
        assign(button7,R.id.button_7);
        assign(button8,R.id.button_8);
        assign(button9,R.id.button_9);
        assign(buttonAC,R.id.button_ac);
        assign(buttonDot,R.id.button_dot);




    }

    void assign(MaterialButton btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();

        String dataToCalculate = solutionTv.getText().toString();

        if (buttonText.equals("AC")) {
            solutionTv.setText("");
            resultTv.setText("0");
            return;
        }
        if (buttonText.equals("=")) {
            solutionTv.setText(resultTv.getText());
            return;
        }
        if (buttonText.equals("C")) {
            dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length() - 1);
        } else {
            dataToCalculate = dataToCalculate + buttonText;
        }
        solutionTv.setText(dataToCalculate);


        String finalResult = getResult(dataToCalculate);

        if(!finalResult.equals("Err")){
            resultTv.setText(finalResult);
        }

    }

    String getResult(String data){
       try {
           Context context = Context.enter();
           context.setOptimizationLevel(-1);
           Scriptable scriptable = context.initStandardObjects();
           String finalResult = context.evaluateString(scriptable,data,"Javascript",1,null).toString();
           if(finalResult.endsWith(".0")){
               finalResult = finalResult.replace(".0","");
           }
           return finalResult;
       }catch (Exception e){
           return "Err";
       }
    }
}