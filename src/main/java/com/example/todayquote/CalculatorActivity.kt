package com.example.todayquote

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText

class CalculatorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator_program)

        val num1 = findViewById<EditText>(R.id.number1)
        val num2 = findViewById<EditText>(R.id.number2)
        val operator = findViewById<EditText>(R.id.operator)
        val calcBtn = findViewById<Button>(R.id.btn)

        // TODO : 계산 버튼에 onclick 리스너 붙여서 Logcat으로
        // num1, num2, operator 값 출력하게 하기

        calcBtn.setOnClickListener{
            Log.d("mytag", num1.text.toString())
            Log.d("mytag", num2.text.toString())
            Log.d("mytag", operator.text.toString())

            val n1 = num1.text.toString().toInt()
            val n2 = num2.text.toString().toInt()
            val intent = Intent(this, CalculateResultActivity::class.java)
            intent.putExtra("n1", n1)
            intent.putExtra("n2", n2)
            intent.putExtra("operator", operator.text.toString())
            startActivity(intent)
        }





    }
}