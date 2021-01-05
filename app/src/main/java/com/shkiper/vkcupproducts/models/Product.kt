package com.shkiper.vkcupproducts.models

data class Product(
    private val id: String,
    val name: String,
    val imagePath: String,
    private val isChosen: Boolean,
    private val description: String
)