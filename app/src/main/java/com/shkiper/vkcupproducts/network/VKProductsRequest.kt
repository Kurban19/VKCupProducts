package com.shkiper.vkcupproducts.network

import com.shkiper.vkcupproducts.models.Product
import com.vk.api.sdk.requests.VKRequest

class VKProductsRequest: VKRequest<List<Product>> {
    constructor(ownerId: String) : super("market.get") {
        addParam("country_id", 1)
        addParam("owner_id", "-$ownerId")
    }
}