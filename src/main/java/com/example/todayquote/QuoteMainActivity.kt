package com.example.todayquote

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.util.*

data class Quote(var idx: Int, var text: String, var from: String? = null){
//    fun method() {}
    companion object{
        fun saveTopPreference(pref: SharedPreferences, idx: Int, text: String, from: String = "") : Quote{
            val editor = pref.edit()

            editor.putString("${idx}.txt", text)
            editor.putString("${idx}.from", from.trim())

            editor.apply()

            return Quote(idx, text, from)
        }

    fun getQuotesFromPreference(pref:SharedPreferences):MutableList<Quote>{
        val quotes = mutableListOf<Quote>()

        for(i in 0 until 20){
            val quoteText = pref.getString("${i}.txt", "")!!
            val quoteFrom = pref.getString("${i}.from", "")!!
            if(quoteText.isNotBlank()){
                quotes.add((Quote(i, quoteText, quoteFrom)))
            }
        }

        return quotes
    }

    fun removeQuoteFromPreference(
        pref: SharedPreferences, idx:Int
    ){
        val editor = pref.edit()
        editor.remove("${idx}.txt")
        editor.remove("${idx}.from")
        editor.apply()
    }
    }
}

class QuoteMainActivity : AppCompatActivity() {
//    액티비티는 생성자 호출을 우리가 할 수 없고, 운영체제가 수행해주므로
//    생성자에서 해당 값을 초기화를 못시켜주니까 lateinit으로 해서
//    나중에 해당 값이 사용 전 반드시 초기화됨을 약속함
    private lateinit var quotes: MutableList<Quote>
    private lateinit var pref: SharedPreferences

    fun initializeQuote(){
        val initialized = pref.getBoolean("initialized", false)
        if(!initialized){
            Quote.saveTopPreference(pref, 0,"삶이 있는 한 희망은 있다", "키케로")
            Quote.saveTopPreference(pref, 1,"진정으로 웃으려면 고통을 참아야하며 , 나아가 고통을 즐길 줄 알아야 해", "찰리 채플린")
            Quote.saveTopPreference(pref, 2,"신은 용기있는자를 결코 버리지 않는다", "켄러")
            val editor = pref.edit()
            editor.putBoolean("initialized", true)
            editor.apply()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.quote_main_activity)

        pref = getSharedPreferences("quotes", Context.MODE_PRIVATE)
        initializeQuote()

        var quote = findViewById<TextView>(R.id.quote)
        var writer = findViewById<TextView>(R.id.writer)

        quotes = Quote.getQuotesFromPreference(pref)

        if(quotes.isNotEmpty()){
            val idx = Random().nextInt(quotes.size)
            val q = quotes[idx]
            quote.text = q.text
            writer.text = q.from
        }else{
            quote.text = "저장된 명언이 없습니다."
            writer.text =""

        }

        val listButton = findViewById<Button>(R.id.quote_list)
        listButton.setOnClickListener {
            val intent = Intent(this, QuoteListActivity::class.java)
//          무언가 가지고 오고 싶은 것이 있을 때 putExtra를 사용함.
            intent.putExtra("quote_size", quotes.size)

//            intent.putExtra("num1", 100)
//            intent.putExtra("num2", 50)
//            intent.putExtra("op", "+")

            startActivity(intent)
        }
        val editButton = findViewById<Button>(R.id.quote_edit_list)
        editButton.setOnClickListener {
            val intent = Intent(this, QuoteEditActivity::class.java)
            startActivity(intent)
        }

//        quotes = mutableListOf()
////      mutableList는 바뀌는 배열의 역할을 함
//
//        var q1 = Quote(1,"삶이 있는 한 희망은 있다", "키케로")
//        quotes.add(q1)
//        quotes.add(Quote(2,"진정으로 웃으려면 고통을 참아야하며 , 나아가 고통을 즐길 줄 알아야 해", "찰리 채플린"))
//        quotes.add(Quote(3,"신은 용기있는자를 결코 버리지 않는다", "켄러"))
//        quotes.add(Quote(4,"단순하게 살아라. 현대인은 쓸데없는 절차와 일 때문에 얼마나 복잡한 삶을 살아가는가?", "이드리스 샤흐"))
//        quotes.add(Quote(5,"만족할 줄 아는 사람은진정한 부자이고, 탐욕스러운 사람은진실로 가난한 사람이다.", "솔론"))
//        quotes.add(Quote(6,"길을 잃는 다는 것은 곧 길을 알게 된다는 것이다.", "동아프리카속담"))
//        quotes.add(Quote(7,"내가 헛되이 보낸 오늘은 어제 죽어간 이들이 그토록 바라던 하루이다 단 하루면 인간적인 모든 것을 멸망시킬수도 다시 소생시킬수도 있다.", "소포클레스"))
//
////      0~2 까지의 랜덤 숫자를 randomIndex에 저장시킨다. (1부터 3하려면 +1하면 됨)
//        var randomIndex = Random().nextInt(quotes.size)
//        val randomQuote = quotes[randomIndex]
//
//        quote.text = randomQuote.text
//        writer.text = randomQuote.from

    }
}