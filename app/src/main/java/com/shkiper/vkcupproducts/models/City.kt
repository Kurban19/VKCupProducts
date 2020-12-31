package com.shkiper.vkcupproducts.models

import org.json.JSONObject

data class City(
    val id: Int,
    val title: String
) {

    companion object{
        fun parse(r: JSONObject): City{
            return City(r.optInt("id", 0), r.optString("title", ""))
        }
    }

}