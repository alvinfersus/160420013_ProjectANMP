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
import com.ubaya.a160420013_projectanmp.model.User

class UserViewModel(application: Application): AndroidViewModel(application) {
    val userData = MutableLiveData<User>()
    val UserLoadError = MutableLiveData<Boolean>()
    val loadingUser = MutableLiveData<Boolean>()

    val TAG = "volleyTagUser"
    private var queue: RequestQueue? = null

    fun refresh(){
        loadingUser.value = true
        UserLoadError.value = false

        queue = Volley.newRequestQueue(getApplication())
        val url = "http://10.0.2.2/anmp/user_list.php?user_id=160420013"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<User>() { }.type
                val result = Gson().fromJson<User>(it, sType)
                userData.value = result

                loadingUser.value = false
                Log.d("showvoleyUser", result.toString())
            },
            {
                Log.d("showvoleyUser", it.toString())
                UserLoadError.value = false
                loadingUser.value = false
            })

        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}