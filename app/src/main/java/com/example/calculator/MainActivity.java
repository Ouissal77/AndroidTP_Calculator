package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    TextView resultScreen,operationScreen;
    Button buttonPercentage,buttonCE,buttonC,buttonDelete,buttonPlus,buttonMinus,buttonDivision,buttonMultiply;
    Button buttonNegate,buttonEqual,buttonDot,buttonOnX,buttonXsquare,buttonSQRT;
    Button button0,button1,button2,button3,button4,button5,button6,button7,button8,button9;
     boolean replacedInitialZero ;
     boolean operatorPressed ;
     boolean equalsPressed ;
     double firstOperand ;
     double secondOperand ;
     String currentOperator;
    Button[] numbers = new Button[10]; // Create an array of Buttons with a size of 10


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultScreen=findViewById(R.id.resultScreen);
        operationScreen=findViewById(R.id.operationScreen);
        replacedInitialZero = false;
        operatorPressed = false;
        equalsPressed = false;
        firstOperand = 0;
        secondOperand = 0;
        currentOperator = "";

//        button0=findViewById(R.id.button0);
//        button1=findViewById(R.id.button1);
//        button2=findViewById(R.id.button2);
//        button3=findViewById(R.id.button3);
//        button4=findViewById(R.id.button4);
//        button5=findViewById(R.id.button5);
//        button6=findViewById(R.id.button6);
//        button7=findViewById(R.id.button7);
//        button8=findViewById(R.id.button8);
//        button9=findViewById(R.id.button9);

        numbers[0] = findViewById(R.id.button0);
        numbers[1] = findViewById(R.id.button1);
        numbers[2] = findViewById(R.id.button2);
        numbers[3] = findViewById(R.id.button3);
        numbers[4] = findViewById(R.id.button4);
        numbers[5] = findViewById(R.id.button5);
        numbers[6] = findViewById(R.id.button6);
        numbers[7] = findViewById(R.id.button7);
        numbers[8] = findViewById(R.id.button8);
        numbers[9] = findViewById(R.id.button9);


        buttonPercentage=findViewById(R.id.buttonPercentage);
        buttonCE=findViewById(R.id.buttonCE);
        buttonC=findViewById(R.id.buttonC);
        buttonDelete=findViewById(R.id.buttonDelete);
        buttonDivision=findViewById(R.id.buttonDivision);
        buttonMinus=findViewById(R.id.buttonMinus);
        buttonMultiply=findViewById(R.id.buttonMultiply);
        buttonPlus=findViewById(R.id.buttonPlus);
        buttonDot=findViewById(R.id.buttonDot);
        buttonOnX=findViewById(R.id.buttonOnX);
        buttonXsquare=findViewById(R.id.buttonXsquare);
        buttonSQRT=findViewById(R.id.buttonSQRT);
        buttonEqual=findViewById(R.id.buttonEqual);
        buttonNegate=findViewById(R.id.buttonNegate);

        ArrayList<Button> operators = new ArrayList<Button>();
        operators.add(buttonPlus);
        operators.add(buttonMinus);
        operators.add(buttonMultiply);
        operators.add(buttonDivision);
        operators.add(buttonSQRT);
        operators.add(buttonXsquare);
        operators.add(buttonOnX);
        operators.add(buttonPercentage);



        setResultScreen();

        for (Button operator : operators) {
            operator.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    if(!operatorPressed){
                        currentOperator=operator.getText().toString();
                        firstOperand = Double.parseDouble(resultScreen.getText().toString());

                        //operate(firstOperand,secondOperand,currentOperator);
                        operationScreen.setText(String.valueOf(firstOperand) + " " +currentOperator);
                        resultScreen.setText(""); // Clear the result screen for the next input
                        operatorPressed=true;

                    }
                    else{
                        if (!resultScreen.getText().toString().isEmpty()) {
                            secondOperand=Double.parseDouble(resultScreen.getText().toString());
                            double result= operate(firstOperand,secondOperand,currentOperator);
                            firstOperand = result;
                            currentOperator=operator.getText().toString();
                            operationScreen.setText(String.valueOf(result) + currentOperator);
                            resultScreen.setText("");
                        } else {
                        secondOperand=0;
                        currentOperator=operator.getText().toString();
                        operationScreen.setText(String.valueOf(firstOperand) + currentOperator);
                        }
                    }
                }
            });
        }



        buttonEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                secondOperand=Double.parseDouble(resultScreen.getText().toString());
                operationScreen.setText(String.valueOf(firstOperand) + currentOperator + String.valueOf(secondOperand) + " =");
                double finalResult=operate(firstOperand,secondOperand,currentOperator);
                resultScreen.setText(String.valueOf(finalResult));
            }
        });
    }
    public double operate(double firstOperand,double secondOperand, String operator){
        if(operator.equals(buttonPlus.getText().toString())){
            return add(firstOperand,secondOperand);
        }
        else if(operator.equals(buttonMinus.getText().toString())){
            return substract(firstOperand,secondOperand);
        }
        else if(operator.equals(buttonMultiply.getText().toString())){
            return multiply(firstOperand,secondOperand);
        }
        else if(operator.equals(buttonDivision.getText().toString())){
            return devide(firstOperand,secondOperand);
        }

        else return -1;
    }
    public void setResultScreen() {

        for ( Button number : numbers) {
            number.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    String buttonText = number.getText().toString();

                    // Get the current text on the result screen
                    String currentText = resultScreen.getText().toString();

                    if (currentText.equals("0") && !replacedInitialZero) {
                        // Replace the initial "0" with the clicked number
                        resultScreen.setText(buttonText);
                        replacedInitialZero = true;
                    } else {
                        // Append the clicked number to the current text
                        String updatedText = currentText + buttonText;
                        resultScreen.setText(updatedText);
                    }
                }
            });
        }
    }
    public double add(double a,double b){
        return a+b;
    }
    public double substract(double a,double b){
        return a-b;
    }
    public double multiply(double a,double b){
        return a*b;
    }
    public double devide(double a,double b){
        if(b!=0){
            return a/b;
        }
        else return -1;
    }






//
//    public void onClick(View view) {
//        Button button =(Button) view;
//        String buttonText = button.getText().toString();
//        String dataToCalculate = operationScreen.getText().toString();
//        resultScreen.append(buttonText);
//
//        if(buttonText.equals("CE")){
//            operationScreen.setText("");
//            resultScreen.setText("0");
//            return;
//        }
//        if(buttonText.equals("=")){
//            operationScreen.setText(resultScreen.getText());
//            return;
//        }
//        if(buttonText.equals("C")){
//            dataToCalculate = dataToCalculate.substring(0,dataToCalculate.length()-1);
//        }else{
//            dataToCalculate = dataToCalculate+buttonText;
//        }
//        operationScreen.setText(dataToCalculate);
//
//        String finalResult = getResult(dataToCalculate);
//
//        if(!finalResult.equals("Err")){
//            resultScreen.setText(finalResult);
//        }
//    }
//
//    String getResult(String data){
//       return "result";
//    }













}