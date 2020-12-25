package com.shkiper.vkcupproducts.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shkiper.vkcupproducts.R
import com.shkiper.vkcupproducts.models.Group
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.rv_group_item.view.*

class GroupAdapter(val listener: (Group) -> Unit): RecyclerView.Adapter<GroupAdapter.UserViewHolder>() {
    private var items: List<Group> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupAdapter.UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val convertView = inflater.inflate(R.layout.rv_group_item, parent, false)
        return UserViewHolder(convertView)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) = holder.bind(items[position], listener)

    fun updateData(data: List<Group>) {

        val diffCallback = object : DiffUtil.Callback() {
            override fun areItemsTheSame(oldPos: Int, newPos: Int): Boolean =
                items[oldPos].id == data[newPos].id

            override fun getOldListSize(): Int = items.size

            override fun getNewListSize(): Int = data.size

            override fun areContentsTheSame(oldPos: Int, newPos: Int): Boolean =
                items[oldPos].hashCode() == data[newPos].hashCode()
        }
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        items = data
        diffResult.dispatchUpdatesTo(this)
    }

    inner class UserViewHolder(convertView: View) : RecyclerView.ViewHolder(convertView),
        LayoutContainer {
        override val containerView: View?
            get() = itemView

        fun bind(group: Group, listener: (Group) -> Unit) {

            Glide.with(itemView)
                .load(group.imagePath)
                .into(itemView.iv_logo)

            itemView.tv_title_of_group.text = group.title
            itemView.tv_is_closed.text == if(group.isClosed) "Закрытая группа" else "Открытая группа"

            itemView.setOnClickListener { listener.invoke(group) }
        }
    }
}