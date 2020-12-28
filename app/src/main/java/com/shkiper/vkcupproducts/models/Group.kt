package com.shkiper.vkcupproducts.models

import android.util.Log
import org.json.JSONObject

data class Group(
    val id: String,
    val title: String,
    val isClosed: Boolean,
    val imagePath:String = "",
    val marketEnabled: Boolean,
    private val city: String,
    private val products: List<Product> = emptyList()
){

    companion object{
        fun parse(r: JSONObject): Group{
            var marketEnabled = false
            var city = ""
            if (r.has("market")) {
                marketEnabled = r.getJSONObject("market").getInt("enabled") == 1
            }
            if(r.has("city")){
                city = r.getJSONObject("city").getString("title")
                Log.d("Tag", city)
            }
             return Group(r.getString("id"), r.getString("name"), r.getInt("is_closed") == 1, r.getString("photo_200"), marketEnabled, city)
        }
    }
}