package com.shkiper.vkcupproducts.ui.groups

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.shkiper.vkcupproducts.R
import com.shkiper.vkcupproducts.models.City
import com.shkiper.vkcupproducts.network.VKCitiesRequest
import com.shkiper.vkcupproducts.ui.adapters.CitiesAdapter
import com.shkiper.vkcupproducts.ui.adapters.GroupAdapter
import com.shkiper.vkcupproducts.ui.main.MainActivity
import com.shkiper.vkcupproducts.viewmodels.GroupsViewModel
import com.vk.api.sdk.VK
import com.vk.api.sdk.VKApiCallback
import com.vk.api.sdk.exceptions.VKApiExecutionException
import kotlinx.android.synthetic.main.fragment_groups.*


class GroupsFragment : Fragment() {

    private lateinit var citiesRecyclerView: RecyclerView
    private lateinit var closeImageButton: ImageView
    private lateinit var bottomSheetDialog: BottomSheetDialog
    private lateinit var groupAdapter: GroupAdapter
    private lateinit var citiesAdapter: CitiesAdapter
    private lateinit var viewModel: GroupsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_groups, container, false)

        initBottomSheetDialog(view)


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
        initViews()

        bottomSheetDialog.show()
    }


    private fun initBottomSheetDialog(view : View){
        bottomSheetDialog = BottomSheetDialog(requireActivity(), R.style.BottomSheetDialogTheme)
        val bottomSheetView = LayoutInflater.from(context).inflate(R.layout.layout_bottom_sheet,
                view.findViewById<LinearLayout>(R.id.bottom_sheet_container))


        closeImageButton = bottomSheetView.findViewById<ImageView>(R.id.closeImageView).apply {
            setOnClickListener{
                bottomSheetDialog.dismiss()
            }
        }

        loadCities(bottomSheetView)
        bottomSheetDialog.setContentView(bottomSheetView)
    }

    private fun loadCities(view: View){
        VK.execute(VKCitiesRequest(), object: VKApiCallback<List<City>> {
            override fun success(result: List<City>) {
                initCitiesRecyclerView(view, result as ArrayList<City>)
            }
            override fun fail(error: Exception) {
                throw KotlinNullPointerException()
            }
        })
    }

    private fun initCitiesRecyclerView(view: View, vkCities : ArrayList<City>) {
        citiesRecyclerView = view.findViewById(R.id.citiesRecyclerView)
        citiesRecyclerView.layoutManager = LinearLayoutManager(activity)
        citiesAdapter = CitiesAdapter(activity as MainActivity, vkCities)
        citiesRecyclerView.adapter = citiesAdapter

        citiesRecyclerView.setHasFixedSize(true)
        citiesRecyclerView.setItemViewCacheSize(20)
        citiesRecyclerView.isDrawingCacheEnabled = true
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

    internal fun hideBottomSheetDialog(){
        bottomSheetDialog.hide()
    }

}
