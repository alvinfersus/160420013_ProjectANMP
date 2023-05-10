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
import com.ubaya.a160420013_projectanmp.model.History

class HistoryListViewModel(application: Application): AndroidViewModel(application) {
    val historyLD = MutableLiveData<ArrayList<History>>()
    val historyLoadErrorLD = MutableLiveData<Boolean>()
    val loadingHistoryLD = MutableLiveData<Boolean>()

    val TAG = "volleyTagHistory"
    private var queue: RequestQueue? = null

    fun refresh(user_id:String?){
        historyLoadErrorLD.value = false
        loadingHistoryLD.value = true

        queue = Volley.newRequestQueue(getApplication())
        val url = "http://10.0.2.2/anmp/history_list.php?user_id="+user_id

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<ArrayList<History>>() { }.type
                val result = Gson().fromJson<ArrayList<History>>(it, sType)
                historyLD.value = result

                loadingHistoryLD.value = false
                Log.d("showvoleyHistory", result.toString())
            },
            {
                Log.d("showvoleyHistory", it.toString())
                historyLoadErrorLD.value = false
                loadingHistoryLD.value = false
            })

        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}