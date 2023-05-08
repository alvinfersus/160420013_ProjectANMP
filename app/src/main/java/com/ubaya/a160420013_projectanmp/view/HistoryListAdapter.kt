package com.ubaya.a160420013_projectanmp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.a160420013_projectanmp.R
import com.ubaya.a160420013_projectanmp.model.Book
import com.ubaya.a160420013_projectanmp.model.History
import com.ubaya.a160420013_projectanmp.util.loadImage

class HistoryListAdapter(val historyList:ArrayList<History>) : RecyclerView.Adapter<HistoryListAdapter.HistoryViewHolder>() {
    class HistoryViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.history_item, parent, false)
        return HistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val txtBookName = holder.view.findViewById<TextView>(R.id.txtBookName)
        val txtWriter = holder.view.findViewById<TextView>(R.id.txtWriter)
        val txtCategory = holder.view.findViewById<TextView>(R.id.txtCategories)
        val txtRating = holder.view.findViewById<TextView>(R.id.txtRate)
        val txtRateHistoryAns = holder.view.findViewById<TextView>(R.id.txtRateHistoryAns)
        val txtReviewAns = holder.view.findViewById<TextView>(R.id.txtReviewAns)
        val imageView = holder.view.findViewById<ImageView>(R.id.imgBook)
        val progressBarBook = holder.view.findViewById<ProgressBar>(R.id.progressBarBook)
        imageView.loadImage(historyList[position].image_url, progressBarBook)

        txtBookName.text = historyList[position].book_name
        txtWriter.text = historyList[position].writer
        txtCategory.text = historyList[position].category
        txtRating.text = "Rating : " + historyList[position].rate.toString()
        txtRateHistoryAns.text = historyList[position].my_rate.toString()
        txtReviewAns.text = historyList[position].my_review.toString()
    }

    override fun getItemCount(): Int {
        return historyList.size
    }

    fun updateBookList(newBookList:ArrayList<History>){
        historyList.clear()
        historyList.addAll(newBookList)
        notifyDataSetChanged()
    }
}