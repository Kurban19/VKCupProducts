package com.shkiper.vkcupproducts.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.shkiper.vkcupproducts.R
import com.shkiper.vkcupproducts.models.City
import com.shkiper.vkcupproducts.network.VKCitiesRequest
import com.shkiper.vkcupproducts.ui.adapters.CitiesAdapter
import com.shkiper.vkcupproducts.ui.groups.GroupsFragment
import com.vk.api.sdk.VK
import com.vk.api.sdk.VKApiCallback
import kotlinx.android.synthetic.main.activity_main.*


open class MainActivity : AppCompatActivity() {

    private lateinit var closeImageButton: ImageView
    private lateinit var citiesAdapter: CitiesAdapter
    private lateinit var citiesRecyclerView: RecyclerView
    private lateinit var bottomSheetDialog: BottomSheetDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        toolbar.setOnClickListener {  }
        showFragment(GroupsFragment(), "GroupsFragmentTAG")
        setToolbarTitle("Магазины")


    }

    private fun initBottomSheetDialog(view : View){
        bottomSheetDialog = BottomSheetDialog(this, R.style.BottomSheetDialogTheme)
        val bottomSheetView = LayoutInflater.from(this).inflate(R.layout.layout_bottom_sheet,
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


    private fun showFragment(fragment: Fragment, tag: String){
        val fragmentTransaction = supportFragmentManager.beginTransaction()


        fragmentTransaction.replace(R.id.mainLayout, fragment, tag)
        fragmentTransaction.addToBackStack(null)

        fragmentTransaction.commit()
    }


    private fun backStackFragment() {
        Log.d("Stack count", supportFragmentManager.backStackEntryCount.toString() + "")
        if (supportFragmentManager.backStackEntryCount == 0) {
            finish()
        }
        supportFragmentManager.popBackStack()
        removeCurrentFragment()
    }

    private fun removeCurrentFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        val currentFrag = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
        if (currentFrag != null) {
            transaction.remove(currentFrag)
        }
        transaction.commit()
    }

    protected fun enableNavigationIcon() {
        toolbar.setNavigationIcon(R.drawable.ic_back_outline_28)
        toolbar.setNavigationOnClickListener{
                backStackFragment()
        }
    }


    protected fun disableNavigationIcon() {
        toolbar.navigationIcon = null
    }

    @SuppressLint("ResourceAsColor")
    protected fun setToolbarTitle(title: String) {
        toolbar.setTitleTextColor(R.color.black)
        supportActionBar?.title = title
    }


}