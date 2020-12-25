package com.shkiper.vkcupproducts.repositories

import androidx.lifecycle.MutableLiveData
import com.shkiper.vkcupproducts.models.Group
import com.shkiper.vkcupproducts.network.VKGroupsCommand
import com.vk.api.sdk.*
object GroupsRepository {

    private val groups = MutableLiveData<List<Group>>()

    init {
        groups.value = fetchGroups()
    }

    private fun fetchGroups(): List<Group>{
        var groups = emptyList<Group>()
        VK.execute(VKGroupsCommand(), object: VKApiCallback<List<Group>>{
            override fun success(result: List<Group>) {
                groups = result
            }

            override fun fail(error: Exception) {
                throw Exception(error.message)
            }

        })
        return groups
    }

    fun loadGroups() = groups

}