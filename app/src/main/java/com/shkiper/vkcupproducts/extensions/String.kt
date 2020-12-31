package com.shkiper.vkcupproducts.extensions

fun String.truncate(i: Int = 28): String{
    if(this.length < 28){
        return this
    }
    val res = this.substring(0, i)
    return "$res..."
}