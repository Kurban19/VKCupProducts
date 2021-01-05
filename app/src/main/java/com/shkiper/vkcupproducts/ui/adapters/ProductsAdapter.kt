package com.shkiper.vkcupproducts.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shkiper.vkcupproducts.R
import com.shkiper.vkcupproducts.models.Product
import kotlinx.android.synthetic.main.rv_group_item.view.*


class ProductsAdapter(private val products: List<Product>) : RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.rv_product_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]
        holder.bind(product)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun bind(product: Product){
            itemView.tv_title_of_group.text = product.name

            Glide.with(itemView)
                    .load(product.imagePath)
                    .into(itemView.iv_logo)
        }

    }


}