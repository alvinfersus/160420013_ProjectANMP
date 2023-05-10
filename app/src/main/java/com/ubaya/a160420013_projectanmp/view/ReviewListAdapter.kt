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
import com.ubaya.a160420013_projectanmp.model.Review
import com.ubaya.a160420013_projectanmp.util.loadImage
import org.w3c.dom.Text
import kotlin.math.floor

class ReviewListAdapter(val reviewList:ArrayList<Review>) : RecyclerView.Adapter<ReviewListAdapter.ReviewViewHolder>() {
    class ReviewViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.review_item, parent, false)
        return ReviewViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        val txtUserName = holder.view.findViewById<TextView>(R.id.txtNameReivewer)
        val txtTime = holder.view.findViewById<TextView>(R.id.txtTime)
        val txtReview = holder.view.findViewById<TextView>(R.id.txtReview)
        val imageView = holder.view.findViewById<ImageView>(R.id.imgProfileReviewer)
        val progressBarReviewer = holder.view.findViewById<ProgressBar>(R.id.progressBarReviewer)
        imageView.loadImage(reviewList[position].image_url, progressBarReviewer)

        txtUserName.text = reviewList[position].user_name
        txtTime.text = reviewList[position].time
        txtReview.text = reviewList[position].review

        var star:Double = reviewList[position].rate.toDouble()
        updateWholeStar(holder, star)
    }

    override fun getItemCount(): Int {
        return reviewList.size
    }

    fun updateReviewList(newReviewList:ArrayList<Review>){
        reviewList.clear()
        reviewList.addAll(newReviewList)
        notifyDataSetChanged()
    }

    fun updateWholeStar(holder:ReviewViewHolder, star:Double){
        val txtRating = holder.view.findViewById<TextView>(R.id.txtRating)
        txtRating.text = star.toString() + " Rating"

        val star1 = holder.view.findViewById<ImageView>(R.id.star1)
        val star2 = holder.view.findViewById<ImageView>(R.id.star2)
        val star3 = holder.view.findViewById<ImageView>(R.id.star3)
        val star4 = holder.view.findViewById<ImageView>(R.id.star4)
        val star5 = holder.view.findViewById<ImageView>(R.id.star5)

        if(star == 1.0){
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