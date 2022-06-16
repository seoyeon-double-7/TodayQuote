package com.example.todayquote

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class LottoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lotto_main_activity)

        var nums = findViewById<TextView>(R.id.lotto_nums)
        val r1=(1..45).random()
        val r2=(1..45).random()
        val r3=(1..45).random()
        val r4=(1..45).random()
        val r5=(1..45).random()
        val r6=(1..45).random()
        val r7=(1..45).random()
        val r8=(1..45).random()
        nums.text = "${r1}-${r2}-${r3}-${r4}-${r5}-${r6}-${r7}-${r8}"
    }
}