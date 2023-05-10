package com.ubaya.a160420013_projectanmp.view

import android.util.Log
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
import kotlin.math.floor

class BookListAdapter(val bookList:ArrayList<Book>) : RecyclerView.Adapter<BookListAdapter.BookViewHolder>() {
    class BookViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.book_list_item, parent, false)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
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

        var star:Double = bookList[position].rate.toString().toDouble()
        updateWholeStar(holder, star)

        btnDetail.setOnClickListener{
            val action = BookListFragmentDirections.actionBookDetail(bookList[position].id.toString())
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

    fun updateWholeStar(holder: BookViewHolder, star:Double){
//        val txtRating = holder.view.findViewById<TextView>(R.id.txtRate)
//        txtRating.text = star.toString() + " Rating"

        val star1 = holder.view.findViewById<ImageView>(R.id.star1)
        val star2 = holder.view.findViewById<ImageView>(R.id.star2)
        val star3 = holder.view.findViewById<ImageView>(R.id.star3)
        val star4 = holder.view.findViewById<ImageView>(R.id.star4)
        val star5 = holder.view.findViewById<ImageView>(R.id.star5)

        if(star == 0.0){
            star1.setImageResource(R.drawable.ic_baseline_star_border_24)
            star2.setImageResource(R.drawable.ic_baseline_star_border_24)
            star3.setImageResource(R.drawable.ic_baseline_star_border_24)
            star4.setImageResource(R.drawable.ic_baseline_star_border_24)
            star5.setImageResource(R.drawable.ic_baseline_star_border_24)
        } else if(star == 1.0){
            star1.setImageResource(R.drawable.ic_baseline_star_24)
            star2.setImageResource(R.drawable.ic_baseline_star_border_24)
            star3.setImageResource(R.drawable.ic_baseline_star_border_24)
            star4.setImageResource(R.drawable.ic_baseline_star_border_24)
            star5.setImageResource(R.drawable.ic_baseline_star_border_24)
        } else if(star == 2.0){
            star1.setImageResource(R.drawable.ic_baseline_star_24)
            star2.setImageResource(R.drawable.ic_baseline_star_24)
            star3.setImageResource(R.drawable.ic_baseline_star_border_24)
            star4.setImageResource(R.drawable.ic_baseline_star_border_24)
            star5.setImageResource(R.drawable.ic_baseline_star_border_24)
        } else if(star == 3.0){
            star1.setImageResource(R.drawable.ic_baseline_star_24)
            star2.setImageResource(R.drawable.ic_baseline_star_24)
            star3.setImageResource(R.drawable.ic_baseline_star_24)
            star4.setImageResource(R.drawable.ic_baseline_star_border_24)
            star5.setImageResource(R.drawable.ic_baseline_star_border_24)
        } else if(star == 4.0){
            star1.setImageResource(R.drawable.ic_baseline_star_24)
            star2.setImageResource(R.drawable.ic_baseline_star_24)
            star3.setImageResource(R.drawable.ic_baseline_star_24)
            star4.setImageResource(R.drawable.ic_baseline_star_24)
            star5.setImageResource(R.drawable.ic_baseline_star_border_24)
        } else if(star == 5.0) {
            star1.setImageResource(R.drawable.ic_baseline_star_24)
            star2.setImageResource(R.drawable.ic_baseline_star_24)
            star3.setImageResource(R.drawable.ic_baseline_star_24)
            star4.setImageResource(R.drawable.ic_baseline_star_24)
            star5.setImageResource(R.drawable.ic_baseline_star_24)
        } else {
            var floorStar = floor(star)
            if(floorStar == 1.0){
                star1.setImageResource(R.drawable.ic_baseline_star_24)
                star2.setImageResource(R.drawable.ic_baseline_star_half_24)
                star3.setImageResource(R.drawable.ic_baseline_star_border_24)
                star4.setImageResource(R.drawable.ic_baseline_star_border_24)
                star5.setImageResource(R.drawable.ic_baseline_star_border_24)
            } else if(floorStar == 2.0){
                star1.setImageResource(R.drawable.ic_baseline_star_24)
                star2.setImageResource(R.drawable.ic_baseline_star_24)
                star3.setImageResource(R.drawable.ic_baseline_star_half_24)
                star4.setImageResource(R.drawable.ic_baseline_star_border_24)
                star5.setImageResource(R.drawable.ic_baseline_star_border_24)
            } else if(floorStar == 3.0){
                star1.setImageResource(R.drawable.ic_baseline_star_24)
                star2.setImageResource(R.drawable.ic_baseline_star_24)
                star3.setImageResource(R.drawable.ic_baseline_star_24)
                star4.setImageResource(R.drawable.ic_baseline_star_half_24)
                star5.setImageResource(R.drawable.ic_baseline_star_border_24)
            } else if(floorStar == 4.0){
                star1.setImageResource(R.drawable.ic_baseline_star_24)
                star2.setImageResource(R.drawable.ic_baseline_star_24)
                star3.setImageResource(R.drawable.ic_baseline_star_24)
                star4.setImageResource(R.drawable.ic_baseline_star_24)
                star5.setImageResource(R.drawable.ic_baseline_star_half_24)
            } else if(floorStar == 5.0) {
                star1.setImageResource(R.drawable.ic_baseline_star_24)
                star2.setImageResource(R.drawable.ic_baseline_star_24)
                star3.setImageResource(R.drawable.ic_baseline_star_24)
                star4.setImageResource(R.drawable.ic_baseline_star_24)
                star5.setImageResource(R.drawable.ic_baseline_star_24)
            }
        }
    }
}