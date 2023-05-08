package com.ubaya.a160420013_projectanmp.model

import com.google.gson.annotations.SerializedName

data class User(
    val id:String?,
    val image_url:String?,
    val email:String,
    val name:String?,
    val birth:String?,
    val phone:String?
)