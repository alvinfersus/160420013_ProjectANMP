package com.ubaya.a160420013_projectanmp.viewmodel

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.fragment.app.FragmentActivity
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

    fun refresh(activities:FragmentActivity, user_id:String?, user_password:String = ""){
        loadingUser.value = true
        UserLoadError.value = false

        queue = Volley.newRequestQueue(getApplication())
        val url = "http://10.0.2.2/anmp/user_list.php?user_id="+user_id+"&password="+user_password

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