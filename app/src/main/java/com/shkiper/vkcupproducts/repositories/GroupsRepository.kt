package com.shkiper.vkcupproducts.repositories

import androidx.lifecycle.MutableLiveData
import com.shkiper.vkcupproducts.models.Group
import com.vk.api.sdk.*
object GroupsRepository {

    private val groups = MutableLiveData<List<Group>>()

    init {
        groups.value = fetchGroups()
    }

    private fun fetchGroups(): List<Group>{
//        val request =

        return emptyList()
    }

    fun loadGroups() = groups

}