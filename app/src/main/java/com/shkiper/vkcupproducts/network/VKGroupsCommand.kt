package com.shkiper.vkcupproducts.network

import android.util.Log
import com.shkiper.vkcupproducts.models.Group
import com.vk.api.sdk.VK
import com.vk.api.sdk.VKApiManager
import com.vk.api.sdk.VKApiResponseParser
import com.vk.api.sdk.VKMethodCall
import com.vk.api.sdk.exceptions.VKApiIllegalResponseException
import com.vk.api.sdk.internal.ApiCommand
import org.json.JSONException
import org.json.JSONObject

class VKGroupsCommand : ApiCommand<List<Group>>() {

    override fun onExecute(manager: VKApiManager): List<Group> {

            val call = VKMethodCall.Builder()
                    .method("groups.get")
                    .args("extended", 1)
                    .args("is_member",0)
                    .args("fields", "photo_200")
                    .args("fields", "market")
                    .version(manager.config.version)
                    .build()
            return manager.execute(call, ResponseApiParser())
    }

    private class ResponseApiParser : VKApiResponseParser<List<Group>> {
        override fun parse(response: String): List<Group> {
            try {
                val ja = JSONObject(response).getJSONObject("response").getJSONArray("items")
                val r = ArrayList<Group>(ja.length())
                Log.d("Tag", response)
                for (i in 0 until ja.length()) {
                    val group = Group.parse(ja.getJSONObject(i))
                    r.add(group)
                }
                return r
            } catch (ex: JSONException) {
                throw VKApiIllegalResponseException(ex)
            }
        }
    }
}