package com.shkiper.vkcupproducts.network

import com.shkiper.vkcupproducts.models.City
import com.vk.api.sdk.requests.VKRequest
import org.json.JSONObject

class VKCitiesRequest: VKRequest<List<City>> {
    constructor() : super("database.getCities") {
        addParam("country_id", 1)
    }

    override fun parse(r: JSONObject): List<City> {
        val response = r.getJSONObject("response")
        val cities = response.getJSONArray("items")
        val result = ArrayList<City>()
        for (i in 0 until cities.length()) {
            result.add(City.parse(cities.getJSONObject(i)))
        }
        return result
    }
}