package com.example.todayquote

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class QuoteAdapter(val dataList:List<Quote>) :
    RecyclerView.Adapter<QuoteAdapter.QuoteItemViewHolder>()
    {
        class QuoteItemViewHolder(val view: View) : RecyclerView.ViewHolder(view){
            lateinit var quote: Quote
            val quoteText = view.findViewById<TextView>(R.id.quote_text)
            val quoteFrom = view.findViewById<TextView>(R.id.quote_from)

            fun bind(q: Quote){
                this.quote = q
                quoteText.text = quote.text
                quoteFrom.text = quote.from
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteItemViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(viewType, parent, false)

            return QuoteItemViewHolder(view)
        }

        override fun onBindViewHolder(holder: QuoteItemViewHolder, position: Int) {
           holder.bind(dataList[position])
        }

//        override fun getItemCount(): Int{
//            return dataList.size
//        }
        override fun getItemCount() = dataList.size

//        override fun getItemViewType(position: Int): Int {
//            return R.layout.quote_list_item
//        }
        override fun getItemViewType (position: Int) = R.layout.quote_list_item
    }
