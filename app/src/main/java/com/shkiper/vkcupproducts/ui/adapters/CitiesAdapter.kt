
package com.shkiper.vkcupproducts.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shkiper.vkcupproducts.R
import com.shkiper.vkcupproducts.models.City
import com.shkiper.vkcupproducts.repositories.GroupsRepository
import com.shkiper.vkcupproducts.ui.groups.GroupsFragment
import com.shkiper.vkcupproducts.ui.main.MainActivity


class CitiesAdapter(private val activity: MainActivity, private var cities : MutableList<City>, private var checked : Array<Int?> = arrayOfNulls(cities.size)) : RecyclerView.Adapter<CitiesAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent?.context).inflate(R.layout.rv_city_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int = cities.size

    override fun onBindViewHolder(holder: CitiesAdapter.ViewHolder, position: Int) {
        val vkCity = cities.get(position)
        holder.bind(vkCity, position)
    }

    inner class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!){

        var cityTitle: TextView? = null
        init {
            cityTitle = itemView!!.findViewById(R.id.cityTextView)
        }
        fun bind(city: City, position: Int){
           cityTitle!!.text = city.title
            itemView.setOnClickListener {
                activity.supportFragmentManager.apply {
                    val groupsFragment = findFragmentByTag("GroupsFragmentTAG") as GroupsFragment
                    GroupsRepository.fetchGroups(city.id)
                    groupsFragment.hideBottomSheetDialog()
                }
            }
        }
    }
}