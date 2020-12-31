package com.shkiper.vkcupproducts.ui.cities

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.shkiper.vkcupproducts.R
import com.shkiper.vkcupproducts.models.City
import com.shkiper.vkcupproducts.network.VKCitiesRequest
import com.vk.api.sdk.VK
import com.vk.api.sdk.VKApiCallback
import com.vk.api.sdk.exceptions.VKApiExecutionException
import ru.bey_sviatoslav.android.vk_cup_task_g.MainActivity

import ru.bey_sviatoslav.android.vk_cup_task_g.R
import ru.bey_sviatoslav.android.vk_cup_task_g.adapters.CitiesAdapter
import ru.bey_sviatoslav.android.vk_cup_task_g.adapters.GroupsAdapter
import ru.bey_sviatoslav.android.vk_cup_task_g.models.VKCity
import ru.bey_sviatoslav.android.vk_cup_task_g.requests.VKCitiesRequest
import ru.bey_sviatoslav.android.vk_cup_task_g.requests.VKMarketsRequest
import ru.bey_sviatoslav.android.vkcuptask1.models.VKGroup


class CitiesFragment(private var cityId : Int = -1) : Fragment() {

    private lateinit var marketsInCityTextView: TextView
    private lateinit var bottomSheetDialog: BottomSheetDialog

    private lateinit var closeImageButton: ImageView

    private lateinit var citiesRecyclerView: RecyclerView

    private lateinit var citiesAdapter : CitiesAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_cities, container, false)

        if(cityId != -1){
            loadGroups()
        }
        initBottomSheetDialog(view)
        initViews(view)
        return view
    }

    private fun initBottomSheetDialog(view : View){
        bottomSheetDialog = BottomSheetDialog(activity, R.style.BottomSheetDialogTheme)
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

    private fun initViews(view : View){
        marketsInCityTextView = view.findViewById<TextView>(R.id.marketsInCity)
        marketsInCityTextView.setOnClickListener {
            bottomSheetDialog.show()
        }
    }

    private fun initCitiesRecyclerView(view: View, vkCities : ArrayList<City>) {
        citiesRecyclerView = view.findViewById<RecyclerView>(R.id.citiesRecyclerView)
        citiesRecyclerView.setLayoutManager(LinearLayoutManager(activity))
        citiesAdapter = CitiesAdapter(Cities)
        citiesRecyclerView.adapter = citiesAdapter

        citiesRecyclerView.setHasFixedSize(true)
        citiesRecyclerView.setItemViewCacheSize(20)
        citiesRecyclerView.isDrawingCacheEnabled = true
    }

    private fun loadCities(view: View){
        VK.execute(VKCitiesRequest(), object: VKApiCallback<List<City>> {
            override fun success(result: List<City>) {
                initCitiesRecyclerView(view, result as ArrayList<City>)
            }
            override fun fail(error: Exception) {
                val a = 1
            }
        })
    }


    internal fun hideBottomSheetDialog(){
        bottomSheetDialog.hide()
    }

    internal fun setCityId(cityId: Int){
        this.cityId = cityId
    }

}
