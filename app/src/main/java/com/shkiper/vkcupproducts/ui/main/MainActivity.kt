package com.shkiper.vkcupproducts.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.shkiper.vkcupproducts.R
import com.shkiper.vkcupproducts.models.City
import com.shkiper.vkcupproducts.network.VKCitiesRequest
import com.shkiper.vkcupproducts.ui.dialogs.CitiesSheetDialog
import com.shkiper.vkcupproducts.ui.groups.GroupsFragment
import com.vk.api.sdk.VK
import com.vk.api.sdk.VKApiCallback
import kotlinx.android.synthetic.main.activity_main.*

open class MainActivity : AppCompatActivity() {

    companion object{
        const val groupsFragmentTAG = "groupsFragmentTAG"

    }


    private lateinit var bottomSheetDialog: CitiesSheetDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        showFragment(GroupsFragment(), groupsFragmentTAG)
        enableNavigationIcon()
    }

     fun showFragment(fragment: Fragment, tag: String = ""){
        supportFragmentManager.commit {
                setCustomAnimations(
                        R.anim.slide_in,
                        R.anim.fade_out,
                        R.anim.fade_in,
                        R.anim.slide_out
                )
            replace(R.id.mainLayout, fragment, tag)
            addToBackStack(null)
        }
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

    fun enableNavigationIcon() {
        toolbar.setNavigationIcon(R.drawable.ic_back_outline_28)
        toolbar.setNavigationOnClickListener{
                backStackFragment()
        }
    }


    fun disableNavigationIcon() {
        toolbar.navigationIcon = null
    }

    fun setToolbarTitle(title: String) {
        tv_toolbar_title.text = title
    }

    fun disableDropDownIcon(){
        ic_dropdown.visibility = View.GONE
    }

    fun enableDropDownIcon(){
        ic_dropdown.visibility = View.VISIBLE
    }


}