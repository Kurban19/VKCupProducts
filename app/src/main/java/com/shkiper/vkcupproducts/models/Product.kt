package com.shkiper.vkcupproducts.models

data class Product(
    private val id: String,
    private val name: String,
    private val imagePath: String,
    private val isChosen: Boolean,
    private val description: String
)