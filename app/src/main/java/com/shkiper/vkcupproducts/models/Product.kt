package com.shkiper.vkcupproducts.models

import org.json.JSONObject

data class Product(
    private val id: Int,
    val title: String,
    val imagePath: String,
    val price: String,
    private val isChosen: Boolean = false,
    private val description: String
){
    companion object{
        fun parse(r: JSONObject): Product{
            var price = "0"
            if(r.has("price")){
                price = r.getJSONObject("price").getString("text")
            }
            return Product(r.optInt("id", 0), r.optString("title", ""), r.optString("thumb_photo", ""), price, description = r.optString("description", ""))
        }
    }

}