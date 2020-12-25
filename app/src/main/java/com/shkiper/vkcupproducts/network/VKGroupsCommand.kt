package com.shkiper.vkcupproducts.network

import com.shkiper.vkcupproducts.models.Group
import com.vk.api.sdk.VK
import com.vk.api.sdk.VKApiManager
import com.vk.api.sdk.VKApiResponseParser
import com.vk.api.sdk.VKMethodCall
import com.vk.api.sdk.exceptions.VKApiIllegalResponseException
import com.vk.api.sdk.internal.ApiCommand
import org.json.JSONException
import org.json.JSONObject

class VKGroupsCommand(private val ids: IntArray = intArrayOf()) : ApiCommand<List<Group>>() {

    override fun onExecute(manager: VKApiManager): List<Group> {
        if (ids.isEmpty()) {
            // if no uids, send user's data
            val call = VKMethodCall.Builder()
                .method("groups.get")
                .args("filter", "hasAddress")
                .version(manager.config.version)
                .build()
            return manager.execute(call, ResponseApiParser())
        }else {
            val result = ArrayList<Group>()
            val chunks = ids.toList().chunked(CHUNK_LIMIT)
            for (chunk in chunks) {
                val call = VKMethodCall.Builder()
                    .method("groups.get")
                    .args("group_ids", chunk.joinToString(","))
                    .args("filter", "hasAddress")
                    .version(manager.config.version)
                    .build()
                result.addAll(manager.execute(call, ResponseApiParser()))
            }
            return result
        }
    }

    companion object {
        const val CHUNK_LIMIT = 900
    }

    private class ResponseApiParser : VKApiResponseParser<List<Group>> {
        override fun parse(response: String): List<Group> {
            try {
                val ja = JSONObject(response).getJSONArray("response")
                val r = ArrayList<Group>(ja.length())
                for (i in 0 until ja.length()) {
                    val user = Group.parse(ja.getJSONObject(i))
                    r.add(user)
                }
                return r
            } catch (ex: JSONException) {
                throw VKApiIllegalResponseException(ex)
            }
        }
    }
}