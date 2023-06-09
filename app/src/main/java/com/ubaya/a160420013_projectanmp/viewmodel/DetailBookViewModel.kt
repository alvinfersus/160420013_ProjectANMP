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
import com.ubaya.a160420013_projectanmp.model.Book

class DetailBookViewModel (application: Application): AndroidViewModel(application){
    val booksLD = MutableLiveData<Book>()

    val TAG = "volleyTagBookDetail"
    private var queue: RequestQueue? = null

    fun fetch(book_id:String){
        queue = Volley.newRequestQueue(getApplication())
        val url = "http://10.0.2.2/anmp/book_list.php?book_id="+book_id

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<Book>() { }.type
                val result = Gson().fromJson<Book>(it, sType)
                booksLD.value = result

                Log.d("showvoleyBookDetail", result.toString())
            },
            {
                Log.d("showvoleyBookDetail", it.toString())
            })

        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}