package com.example.calculatorapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onDigit(view: View){
        calculation_view.append((view as Button).text)
    }
    fun onClear(view: View){
        calculation_view.setText("")
    }

    fun onDecimal(view: View){
        if(calculation_view.text.contains(".")|| calculation_view.text==""){
            calculation_view.text
        } else{
            calculation_view.append(".")
        }
    }
}