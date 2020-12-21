package com.shkiper.vkcupproducts.repositories

import androidx.lifecycle.MutableLiveData
import com.shkiper.vkcupproducts.models.Group

object GroupsRepository {

    private val groups = MutableLiveData<List<Group>>()

    init {
        groups.value = fetchGroups()
    }

    private fun fetchGroups(): List<Group>{
        //TODO complete function to retrieve groups from vk

        return emptyList()
    }

    fun loadGroups() = groups

}