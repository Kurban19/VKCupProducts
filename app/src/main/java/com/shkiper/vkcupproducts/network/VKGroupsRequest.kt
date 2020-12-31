package com.shkiper.vkcupproducts.network

import android.util.Log
import com.shkiper.vkcupproducts.models.Group
import com.vk.api.sdk.VK
import com.vk.api.sdk.VKApiManager
import com.vk.api.sdk.VKApiResponseParser
import com.vk.api.sdk.VKMethodCall
import com.vk.api.sdk.exceptions.VKApiIllegalResponseException
import com.vk.api.sdk.internal.ApiCommand
import com.vk.api.sdk.requests.VKRequest
import org.json.JSONException
import org.json.JSONObject

class VKGroupsRequest: VKRequest<List<Group>> {

    constructor(cityId : Int) : super("groups.search") {
        addParam("q", " ")
        addParam("country_id", 1)
        addParam("city_id", cityId)
        addParam("market", 1)
    }

    override fun parse(r: JSONObject): List<Group> {
        val response = r.getJSONObject("response")
        val groups = response.getJSONArray("items")
            val result = ArrayList<Group>()
        for (i in 0 until groups.length()) {
            result.add(Group.parse(groups.getJSONObject(i)))
        }
        return result
    }
}