package com.shkiper.vkcupproducts.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.shkiper.vkcupproducts.R
import com.shkiper.vkcupproducts.ui.groups.GroupsFragment
import com.vk.api.sdk.utils.VKUtils.getCertificateFingerprint
import kotlinx.android.synthetic.main.activity_main.*


open class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        showFragment(GroupsFragment())
        setToolbarTitle("Магазины в Санкт-Петербурге")
    }

    private fun showFragment(fragment: Fragment){
        val TAG = fragment.tag
        val fragmentTransaction = supportFragmentManager.beginTransaction()

        fragmentTransaction.replace(R.id.nav_host_fragment, fragment, TAG)
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