package com.ubaya.a160420013_projectanmp.model

data class Review(
    val user_id:String,
    val user_name:String,
    val time:String,
    val rate:String,
    val image_url:String,
    val book_id:String,
    val book_name:String,
    val review:String
)