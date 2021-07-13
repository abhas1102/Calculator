package com.example.calculatorapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ArithmeticException


class MainActivity : AppCompatActivity() {

    var lastNumeric = false
    var lastDot = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onDigit(view: View){
        calculation_view.append((view as Button).text)
        lastNumeric = true
    }
    fun onClear(view: View){
        calculation_view.setText("")
        lastNumeric = false
        lastDot = false
    }

    fun onDecimal(view: View){
        if(lastNumeric && !lastDot){
            calculation_view.append(".")
            lastNumeric = false
            lastDot = true
        }

    }

    fun onOperator(view: View){
        if (lastNumeric && !isOperatorAdded(calculation_view.text.toString())){
            calculation_view.append((view as Button).text)
            lastNumeric = false
            lastDot = false
        }
    }

    private fun isOperatorAdded(value:String):Boolean{
        return if(value.startsWith("-")){
            false
        }
        else{
            value.contains("/")||value.contains("*")||value.contains("+")
                    ||value.contains("-")
        }
    }

    fun OnEqual(view: View){
        if(lastNumeric){
            var calculationValue = calculation_view.text.toString()
            var prefix = ""
            try {
                if (calculationValue.startsWith("-")){
                    prefix = "-"
                    calculationValue = calculationValue.substring(1)
                }

                if(calculationValue.contains("-")){
                    val splitValue = calculationValue.split("-")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if(!prefix.isEmpty()){
                        one = prefix+one
                    }

                    calculation_view.text = removeZeroAfterDot((one.toDouble() - two.toDouble()).toString())
                } else if(calculationValue.contains("+")){
                    val splitValue = calculationValue.split("+")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if(!prefix.isEmpty()){
                        one = prefix+one
                    }

                    calculation_view.text = removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())
            }else if(calculationValue.contains("*")) {
                    val splitValue = calculationValue.split("*")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (!prefix.isEmpty()) {
                        one = prefix + one
                    }

                    calculation_view.text = removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())
                }else if(calculationValue.contains("/")) {
                    val splitValue = calculationValue.split("/")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (!prefix.isEmpty()) {
                        one = prefix + one
                    }

                    calculation_view.text = removeZeroAfterDot((one.toDouble() / two.toDouble()).toString())
                }}catch (e:ArithmeticException){
                e.printStackTrace()
            }
        }
    }

    private fun removeZeroAfterDot(result:String):String{
        var value = result
        if(result.contains(".0")){
            value = result.substring(0,result.length-2)

        }
        return value
    }


}

//Dot shouldn't be applied if there is nothing on screen
//Only one dot should be there in a number
//After dot there should be a number
//only