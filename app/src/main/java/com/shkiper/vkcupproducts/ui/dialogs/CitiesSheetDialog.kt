package com.shkiper.vkcupproducts.ui.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.shkiper.vkcupproducts.R
import com.shkiper.vkcupproducts.models.City
import com.shkiper.vkcupproducts.ui.adapters.CitiesAdapter
import com.shkiper.vkcupproducts.ui.main.MainActivity
import kotlinx.android.synthetic.main.layout_bottom_sheet.*


class CitiesSheetDialog(private val cities: List<City>) : BottomSheetDialogFragment() {

    private lateinit var citiesAdapter: CitiesAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.BottomSheetDialogTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.layout_bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }


    private fun initViews(){
        closeImageView.setOnClickListener {
            dismiss()
        }
        citiesRecyclerView.layoutManager = LinearLayoutManager(activity)
        citiesAdapter = CitiesAdapter(activity as MainActivity, cities as MutableList<City>)
        citiesRecyclerView.adapter = citiesAdapter

        citiesRecyclerView.setHasFixedSize(true)
        citiesRecyclerView.setItemViewCacheSize(20)
        citiesRecyclerView.isDrawingCacheEnabled = true
    }

}