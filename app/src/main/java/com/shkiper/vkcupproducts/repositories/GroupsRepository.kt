package com.shkiper.vkcupproducts.repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.shkiper.vkcupproducts.models.Group
import com.shkiper.vkcupproducts.network.VKGroupsCommand
import com.vk.api.sdk.*
object GroupsRepository {

    private val groups = MutableLiveData<List<Group>>()

    init {
        fetchGroups()
    }

    private fun fetchGroups(){
        VK.execute(VKGroupsCommand(), object: VKApiCallback<List<Group>>{
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