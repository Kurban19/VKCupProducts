package com.shkiper.vkcupproducts.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.shkiper.vkcupproducts.models.Group
import com.shkiper.vkcupproducts.repositories.GroupsRepository

class GroupsViewModel: ViewModel() {

    private val groupsRepository = GroupsRepository


    private val groups = Transformations.map(groupsRepository.loadGroups()) { groups ->
        return@map groups.filter { it.marketEnabled }
    }

    fun getGroups(): LiveData<List<Group>> {
        return groups
    }


}