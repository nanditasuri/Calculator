/*
@Nandita
@Calculator doing basic operations

 */

package com.example.nandita.calculator;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/*Main activity to create the calculator layout and operations
This class extends Activity and as well implements OnClickListener
 */
public class MainActivity extends Activity implements View.OnClickListener {

    String showTxt="";
    String numVal="";
    /*ArrayList of operations that are clicked*/
    ArrayList<String> opList;
    Boolean isDivideOn;

/* Setting onClickListener to all the buttons in calculator
using a for loop
 */
    public void setClickListenersToButtons(){

        int i;
        int[] buttonIds = {R.id.button_Num1,R.id.button_Num2,R.id.button_Num3,R.id.button_Num4,R.id.button_Num5,
                R.id.button_Num6,R.id.button_Num7,R.id.button_Num8,R.id.button_Num9,R.id.button_Num0,
                R.id.button_Add,R.id.button_Subtract,R.id.button_Multiply,R.id.button_Divide,
                R.id.button_Back,R.id.button_Clear,R.id.button_Result};

        for(i=0;i<17;i++){
            System.out.println("In loop button id is "+buttonIds[i] );

            Button b = (Button)findViewById(buttonIds[i]);
            b.setOnClickListener(this);
        }
    }
    @Override
    /*onCreate function */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        opList = new ArrayList();
        isDivideOn = Boolean.FALSE;
        setClickListenersToButtons();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    /*onClick call back which is called when buttons in the app are clicked.
    Having the logic for different buttons using switch
     */
    public void onClick(View v) {

        EditText et;
        Button b;
        int bId;
        float r;
        int int_r;
        String bText;

        bId = v.getId();

        /*Get the EditText and Button references*/
        et = (EditText)findViewById(R.id.editText);
        b = (Button)findViewById(bId);

        switch (bId){
            case R.id.button_Add: {
                opList.add(numVal);
                bText = b.getText().toString();
                opList.add(bText);
                showTxt += bText;
                et.setText(showTxt);
                numVal = "";
                break;
            }
            case R.id.button_Subtract:{
                opList.add(numVal);
                bText = b.getText().toString();
                opList.add(bText);
                showTxt += bText;
                et.setText(showTxt);
                numVal="";
                break;
            }
            case R.id.button_Multiply:{
                opList.add(numVal);
                bText = b.getText().toString();
                opList.add(bText);
                showTxt += bText;
                et.setText(showTxt);
                numVal="";
                break;
            }
            case R.id.button_Divide:{
                isDivideOn = Boolean.TRUE;
                opList.add(numVal);
                bText = b.getText().toString();
                opList.add(bText);
                showTxt += bText;
                et.setText(showTxt);
                numVal="";
                break;
            }
            case R.id.button_Result:{
                opList.add(numVal);
                numVal ="";
                r = performOperation();
                if(!isDivideOn) {
                    int_r = (int)r;
                    et.setText(String.valueOf(int_r));
                }
                else {
                    et.setText(String.valueOf(r));
                }

                break;
            }
            case R.id.button_Clear:{
                opList.clear();
                showTxt="";
                numVal = "";
                et.setText(showTxt);
                break;
            }
            case R.id.button_Back:{
                System.out.println("In back");
                break;

            }
            default:{
              /* When button0 to button 9 are clicked*/
                bText = b.getText().toString();
                showTxt += bText;
                numVal += bText;
                et.setText(showTxt);
                break;
            }
        }
    }
    /*when result(=) is pressed,the MADS
    operations are performed
     */
    public float performOperation(){

        int i,j;
        float res;

        res = Float.parseFloat(opList.get(0));

        for(i=0;i<opList.size();i++)
            System.out.println("the opValue at " + i +"is "+opList.get(i));
        for(i=1; i < opList.size();i = i+2){

            j=i;
            if("+".contentEquals(opList.get(i)))
                res = res + Float.parseFloat(opList.get(++j));
            else if("-".contentEquals(opList.get(i)))
                res = res - Float.parseFloat(opList.get(++j));
            else if("*".contentEquals(opList.get(i)))
                res = res * Float.parseFloat(opList.get(++j));
            else if("/".contentEquals(opList.get(i))) {
                try {
                    res = res / Float.parseFloat(opList.get(++j));
                    /*For floats, divide by zero exception is not thrown,
                    so handling it here
                     */
                    if(Float.isInfinite(res)){
                        Toast.makeText(this,"Cannot divide by zero",Toast.LENGTH_LONG).show();
                        res = 0;
                        isDivideOn = Boolean.FALSE;
                    }

                }catch(ArithmeticException e){
                    System.out.println("Divide by zero");
                    Toast.makeText(this,"Cannot divide by zero",Toast.LENGTH_LONG).show();
                    res = 0;
                }

            }
        }
        opList.clear();
        numVal = Float.toString(res);
        showTxt = numVal;
        return res;

    }

}
