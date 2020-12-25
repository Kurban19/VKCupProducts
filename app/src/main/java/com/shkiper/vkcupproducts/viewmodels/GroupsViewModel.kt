package com.shkiper.vkcupproducts.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shkiper.vkcupproducts.models.Group
import com.shkiper.vkcupproducts.repositories.GroupsRepository

class GroupsViewModel: ViewModel() {

    private val groupsRepository = GroupsRepository


    private val groups = groupsRepository.loadGroups()


    fun getGroups(): MutableLiveData<List<Group>> {
        return groups
    }


}