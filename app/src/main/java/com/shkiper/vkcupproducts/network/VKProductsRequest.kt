package com.shkiper.vkcupproducts.network

import com.shkiper.vkcupproducts.models.Product
import com.vk.api.sdk.requests.VKRequest
import org.json.JSONObject

class VKProductsRequest: VKRequest<List<Product>> {
    constructor(ownerId: String) : super("market.get") {
        addParam("country_id", 1)
        addParam("owner_id", "-$ownerId")
    }

    override fun parse(r: JSONObject): List<Product> {
        val response = r.getJSONObject("response")
        val products = response.getJSONArray("items")
        val result = ArrayList<Product>()
        for (i in 0 until products.length()) {
            result.add(Product.parse(products.getJSONObject(i)))
        }
        return result
    }

}