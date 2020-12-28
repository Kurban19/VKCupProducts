package com.shkiper.vkcupproducts.ui.groups

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.shkiper.vkcupproducts.R
import com.shkiper.vkcupproducts.ui.adapters.GroupAdapter
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

    }

    private fun initViews(){
        groupAdapter = GroupAdapter{
            Toast.makeText(this.context, it.title, Toast.LENGTH_LONG).show()
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

}
