package com.shkiper.vkcupproducts.models

import android.os.Parcel
import android.os.Parcelable
import org.json.JSONObject

data class Product(
    private val id: Int,
    val title: String,
    val imagePath: String,
    val price: String,
//    private val isChosen: Boolean = false,
    private val description: String
) : Parcelable {


    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString() ?: "",
            parcel.readString()!!,
            parcel.readString()!!,
            parcel.readString()!!)


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeString(imagePath)
        parcel.writeString(price)
        parcel.writeString(description)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Product>{


        fun parse(r: JSONObject): Product{
            var price = "0"
            if(r.has("price")){
                price = r.getJSONObject("price").getString("text")
            }
            return Product(r.optInt("id", 0), r.optString("title", ""), r.optString("thumb_photo", ""), price, description = r.optString("description", ""))
        }

        override fun createFromParcel(source: Parcel): Product {
            return Product(source)
        }

        override fun newArray(size: Int): Array<Product?> {
            return arrayOfNulls(size)
        }
    }

}