package com.shkiper.vkcupproducts.models

data class Group(
    private val id: String,
    private val name: String,
    private val imagePath:String,
    private val city: String,
    private val products: ArrayList<Product>
)