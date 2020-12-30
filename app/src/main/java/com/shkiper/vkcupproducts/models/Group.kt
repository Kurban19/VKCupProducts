package com.shkiper.vkcupproducts.models

import org.json.JSONObject

data class Group(
    val id: String,
    val title: String,
    val isClosed: Boolean,
    val imagePath:String = "",
    val marketEnabled: Boolean,
    private val city: String = ""
){

    companion object{
        fun parse(r: JSONObject): Group{
            var marketEnabled = false
            if(r.has("market")) {
                marketEnabled = r.getJSONObject("market").getInt("enabled") == 1
            }
             return Group(r.getString("id"), r.getString("name"), r.getInt("is_closed") == 1, r.getString("photo_200"), marketEnabled)
        }
    }
}