package com.shkiper.vkcupproducts.ui.groups

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.shkiper.vkcupproducts.R
import com.shkiper.vkcupproducts.models.City
import com.shkiper.vkcupproducts.network.VKCitiesRequest
import com.shkiper.vkcupproducts.ui.adapters.GroupAdapter
import com.shkiper.vkcupproducts.ui.dialogs.CitiesSheetDialog
import com.shkiper.vkcupproducts.ui.group.GroupFragment
import com.shkiper.vkcupproducts.ui.main.MainActivity
import com.shkiper.vkcupproducts.viewmodels.GroupsViewModel
import com.vk.api.sdk.VK
import com.vk.api.sdk.VKApiCallback
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_groups.*


class GroupsFragment : Fragment() {

    private lateinit var dropDownIV: ImageView
    private lateinit var bottomSheetDialog: CitiesSheetDialog
    private lateinit var groupAdapter: GroupAdapter
    private lateinit var viewModel: GroupsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        initBottomSheetDialog()
        initViewModel()

        return inflater.inflate(R.layout.fragment_groups, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()

    }


    private fun initBottomSheetDialog(){
        loadCities()
    }


    private fun loadCities(){
        VK.execute(VKCitiesRequest(), object: VKApiCallback<List<City>> {
            override fun success(result: List<City>) {
                bottomSheetDialog = CitiesSheetDialog(result)
//                bottomSheetDialog.show(requireActivity().supportFragmentManager, "OpenSheetDialog")
            }
            override fun fail(error: Exception) {
                throw KotlinNullPointerException()
            }
        })
    }





    private fun initViews(){
        groupAdapter = GroupAdapter{
            val bundle = Bundle()
            bundle.apply {
                putString(GroupFragment.GROUP_ID, it.id)
                putString(GroupFragment.GROUP_TITLE, it.title)
            }

            val groupFragment = GroupFragment()
            groupFragment.arguments = bundle
            (activity as MainActivity?)!!.showFragment(groupFragment)
        }

        dropDownIV = requireActivity().findViewById(R.id.ic_dropdown) ?: throw KotlinNullPointerException()

        dropDownIV.setOnClickListener {
            bottomSheetDialog.show(requireActivity().supportFragmentManager, "OpenSheetDialog")
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


    fun hideBottomSheetDialog() {
        bottomSheetDialog.dismiss()
    }


    override fun onResume() {
        super.onResume()
        (activity as MainActivity?)!!.disableNavigationIcon()
        (activity as MainActivity?)!!.enableDropDownIcon()
        (activity as MainActivity?)!!.setToolbarTitle("Магазины")
    }

}
