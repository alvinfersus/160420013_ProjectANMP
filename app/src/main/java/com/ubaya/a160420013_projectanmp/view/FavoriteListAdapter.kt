package com.ubaya.a160420013_projectanmp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.a160420013_projectanmp.R
import com.ubaya.a160420013_projectanmp.model.Book
import com.ubaya.a160420013_projectanmp.util.loadImage

class FavoriteListAdapter(val bookList:ArrayList<Book>) : RecyclerView.Adapter<FavoriteListAdapter.FavViewHolder>() {
    class FavViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.book_list_item, parent, false)
        return FavViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavViewHolder, position: Int) {
        val txtBookName = holder.view.findViewById<TextView>(R.id.txtBookName)
        val txtWriter = holder.view.findViewById<TextView>(R.id.txtWriter)
        val txtCategory = holder.view.findViewById<TextView>(R.id.txtCategories)
        val txtRating = holder.view.findViewById<TextView>(R.id.txtRate)
        val btnDetail = holder.view.findViewById<Button>(R.id.btnDetail)
        val imageView = holder.view.findViewById<ImageView>(R.id.imgBook)
        val progressBarBook = holder.view.findViewById<ProgressBar>(R.id.progressBarBook)
        imageView.loadImage(bookList[position].image_url, progressBarBook)

        txtBookName.text = bookList[position].book_name
        txtWriter.text = bookList[position].writer
        txtCategory.text = bookList[position].category
        txtRating.text = "Rating : " + bookList[position].rate.toString()

        btnDetail.setOnClickListener{
            val action = FavoriteFragmentDirections.actionBookDetail(bookList[position].id.toString())
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    fun updateBookList(newBookList:ArrayList<Book>){
        bookList.clear()
        bookList.addAll(newBookList)
        notifyDataSetChanged()
    }
}