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
import com.shkiper.vkcupproducts.ui.dialogs.CitiesSheetDialog
import com.shkiper.vkcupproducts.ui.groups.GroupsFragment
import com.vk.api.sdk.VK
import com.vk.api.sdk.VKApiCallback
import kotlinx.android.synthetic.main.activity_main.*

open class MainActivity : AppCompatActivity() {

    private lateinit var bottomSheetDialog: CitiesSheetDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        showFragment(GroupsFragment(), "GroupsFragmentTAG")
        setToolbarTitle("Магазины")
        initBottomSheetDialog()
        initViews()
    }

    private fun initBottomSheetDialog(){
        loadCities()
    }


    private fun loadCities(){
        VK.execute(VKCitiesRequest(), object: VKApiCallback<List<City>> {
            override fun success(result: List<City>) {
                bottomSheetDialog = CitiesSheetDialog(result)
            }
            override fun fail(error: Exception) {
                throw KotlinNullPointerException()
            }
        })
    }


    private fun initViews(){
        toolbar.setOnClickListener {
            bottomSheetDialog.show(supportFragmentManager, "OpenSheetDialog")
        }

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


    fun hideBottomSheetDialog(){
        bottomSheetDialog.dismiss()
    }

    fun disableNavigationIcon() {
        toolbar.navigationIcon = null
    }

    @SuppressLint("ResourceAsColor")
    protected fun setToolbarTitle(title: String) {
        toolbar.setTitleTextColor(R.color.black)
        supportActionBar?.title = title
    }


}