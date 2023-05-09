package com.ubaya.a160420013_projectanmp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ubaya.a160420013_projectanmp.model.Review

class ReviewListModel(application: Application): AndroidViewModel(application) {
    val reviewLD = MutableLiveData<ArrayList<Review>>()
    val reviewLoadErrorLD = MutableLiveData<Boolean>()
    val loadingReviewLD = MutableLiveData<Boolean>()

    val TAG = "volleyTagReview"
    private var queue: RequestQueue? = null

    fun refresh(book_id:String){
        loadingReviewLD.value = true
        reviewLoadErrorLD.value = false

        queue = Volley.newRequestQueue(getApplication())
        val url = "http://10.0.2.2/anmp/review_list.php?book_id="+book_id

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<ArrayList<Review>>() { }.type
                val result = Gson().fromJson<ArrayList<Review>>(it, sType)
                reviewLD.value = result

                loadingReviewLD.value = false
                Log.d("showvoleyReview", result.toString())
            },
            {
                Log.d("showvoleyReview", it.toString())
                reviewLoadErrorLD.value = false
                loadingReviewLD.value = false
            })

        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}