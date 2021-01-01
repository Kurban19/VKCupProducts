package com.shkiper.vkcupproducts.repositories

import androidx.lifecycle.MutableLiveData
import com.shkiper.vkcupproducts.models.Group
import com.shkiper.vkcupproducts.network.VKGroupsRequest
import com.vk.api.sdk.*


object GroupsRepository {

    private val groups = MutableLiveData<List<Group>>()

    fun fetchGroups(cityId: Int){
        VK.execute(VKGroupsRequest(cityId), object: VKApiCallback<List<Group>>{
            override fun success(result: List<Group>) {
                groups.value = result
            }
            override fun fail(error: Exception) {
                throw Exception(error.message)
            }

        })
    }

    fun loadGroups() = groups

}