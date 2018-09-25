package com.example.rubyt.hw2;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity {
    Button btn;
    TextView lblGrade;
    EditText[] grades = new EditText[5];
    Boolean [] inputAns = new Boolean[grades.length];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Fill grades array with id from the EditTexts ids
        grades[0] =(EditText)findViewById(R.id.ed1);
        grades[1] =(EditText)findViewById(R.id.ed2);
        grades[2] =(EditText)findViewById(R.id.ed3);
        grades[3] =(EditText)findViewById(R.id.ed4);
        grades[4] =(EditText)findViewById(R.id.ed5);
        lblGrade=(TextView)findViewById(R.id.textView);
        btn = (Button)findViewById(R.id.btn_submit);
    }
        // method converts values to double in array and perform calculations
    public double gpaCalc(EditText[] numsArray){
        double total = 0.0;
        for(int i = 0; i < numsArray.length; i++ )
            total += Double.parseDouble(grades[i].getText().toString());
        return total/numsArray.length;
    }
        //validate input based on input type and number range
    public void validateInput(){
        // use for loop to check input from array
        for (int i = 0; i < grades.length; i++)
            //TextUtils.isEmpty Returns true if the string is null or 0-length.
            if (TextUtils.isEmpty(grades[i].getText().toString())) {
                grades[i].setError("Field can't be empty!");
                inputAns[i] = false;
                // TextUtils.isDigitsOnly Returns a boolean value whether the given CharSequence contains only digits.
            }else if(!TextUtils.isDigitsOnly((grades[i].getText().toString()))){
                grades[i].setError("Field must be a number!");
                inputAns[i] =false;
                //if values in array are not between 0 and 100, then set an error message
            } else if (Double.parseDouble(grades[i].getText().toString()) > 100 || Double.parseDouble(grades[i].getText().toString()) < 0) {
                grades[i].setError("Field Must be between 0 and 100!");
                inputAns[i] = false;
            } else
                inputAns[i] = true; // returns true into array all validations are passed
    }

    public void submit_method(View view) {
        validateInput();
        // If all values from boolean array are true, then call gpaCalc method to do calculations
        if(inputAns[0] && inputAns[1] && inputAns[2] && inputAns[3] && inputAns[4]) {
            double gpa = gpaCalc(grades);
            // use if else statements to determine what color to change based on calculation results
            if (gpa >= 80)
                lblGrade.setBackgroundColor(Color.GREEN);
            else if (gpa >= 61)
                lblGrade.setBackgroundColor(Color.YELLOW);
            else
                lblGrade.setBackgroundColor(Color.RED);

            lblGrade.setText("" + gpa);
            btn.setText(R.string.btn_clear);
        }
        else {
            // false boolean values will set label as an error message
            lblGrade.setText(R.string.lbl_result_error);
            lblGrade.setBackgroundColor(Color.DKGRAY);
            lblGrade.setTextColor(Color.WHITE);
        }
    }
        //method will set text of button to value of the Resource string called but text must be tapped twice
    public void onTouchText(View view){
        btn.setText(R.string.btn_submit);
    }
}
