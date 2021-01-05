package com.shkiper.vkcupproducts.ui.groups

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.shkiper.vkcupproducts.R
import com.shkiper.vkcupproducts.repositories.GroupsRepository
import com.shkiper.vkcupproducts.ui.adapters.GroupAdapter
import com.shkiper.vkcupproducts.ui.group.GroupFragment
import com.shkiper.vkcupproducts.ui.main.MainActivity
import com.shkiper.vkcupproducts.viewmodels.GroupsViewModel
import kotlinx.android.synthetic.main.fragment_groups.*


class GroupsFragment : Fragment() {


    private lateinit var groupAdapter: GroupAdapter
    private lateinit var viewModel: GroupsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_groups, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
        initViews()

        GroupsRepository.fetchGroups(1)

    }



    private fun initViews(){
        groupAdapter = GroupAdapter{
            val bundle = Bundle()
            bundle.putString(GroupFragment.GROUP_ID, it.id)
            val groupFragment = GroupFragment()
            groupFragment.arguments = bundle
            (activity as MainActivity?)!!.showFragment(groupFragment)

        }


        with(rv_list_of_groups){
            adapter = groupAdapter
            layoutManager = LinearLayoutManager(this@GroupsFragment.context)
        }

    }

    private fun initViewModel(){
        viewModel = ViewModelProviders.of(this).get(GroupsViewModel::class.java)
        viewModel.getGroups().observe(viewLifecycleOwner, Observer { groupAdapter.updateData(it) })
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity?)!!.disableNavigationIcon()
        (activity as MainActivity?)!!.enableDropDownIcon()
    }

}
