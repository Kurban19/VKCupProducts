package com.shkiper.vkcupproducts.network

import com.shkiper.vkcupproducts.models.Group
import com.vk.api.sdk.VK
import com.vk.api.sdk.requests.VKRequest
import org.json.JSONObject

class VKGroupsRequest: VKRequest<List<Group>> {

    constructor( ids: IntArray = intArrayOf()): super("groups.get"){
        if (ids.isNotEmpty()) {
            addParam("user_ids", ids.joinToString(","))
        }
        addParam("fields", "photo_200")
    }


    override fun parse(r: JSONObject): List<Group> {
        val users = r.getJSONArray("response")
        val result = ArrayList<Group>()

        for (i in 0 until users.length()) {
            result.add(Group.parse(users.getJSONObject(i)))
        }


        return result
    }

}