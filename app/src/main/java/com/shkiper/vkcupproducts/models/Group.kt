package com.shkiper.vkcupproducts.models

import org.json.JSONObject

data class Group(
    val id: String,
    val title: String,
    val isClosed: Boolean,
    val imagePath:String = "",
    val marketEnabled: Boolean,
    val mainAlbumId: Int,
    private val city: String = ""
){

    companion object{
        fun parse(r: JSONObject): Group{
            var marketEnabled = false
            var mainAlbumId = 0
            if (r.has("market")) {
                r.getJSONObject("market").apply {
                    marketEnabled = this.getInt("enabled") == 1
                    if(this.has("main_album_id")){
                        mainAlbumId = this.getInt("main_album_id")
                    }
                }

            }

             return Group(r.getString("id"), r.getString("name"), r.getInt("is_closed") == 1, r.getString("photo_200"), marketEnabled, mainAlbumId)
        }
    }
}