package com.ubaya.a160420013_projectanmp.model
import com.google.gson.annotations.SerializedName

data class Book(
    val id:String?,
    val book_name:String?,
    val writer:String?,
    val rate:String?,
    val image_url:String?,
    @SerializedName("sinopsis")
    val synopsis:String?,
    val category:String?,
    val edition:String?,
    val languages:String?,
    val pages:String?,
    val publisher:String?
)
