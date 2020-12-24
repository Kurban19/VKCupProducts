package com.shkiper.vkcupproducts.models

import org.json.JSONObject

data class Group(
    val id: String,
    val title: String,
    val imagePath:String,
    private val city: String,
    private val products: List<Product> = emptyList()
){

    companion object{
        fun parse(r: JSONObject): Group{
             return Group(r.getString("id"), r.getString("title"), r.getString("path"), r.getString("city"))
        }
    }


}