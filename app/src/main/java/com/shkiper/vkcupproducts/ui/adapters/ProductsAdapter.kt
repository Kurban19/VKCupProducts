package com.shkiper.vkcupproducts.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
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

        var productTitle: TextView? = null
        var productImage: ImageView? = null
        var productPrice: TextView? = null
        init {
            productTitle = itemView.findViewById(R.id.tv_title_of_product)
            productPrice = itemView.findViewById(R.id.tv_price_product)
            productImage = itemView.findViewById(R.id.iv_product_image)
        }
        fun bind(product: Product){
            productTitle!!.text = product.title
            productPrice!!.text = product.price

            Glide.with(itemView)
                    .load(product.imagePath)
                    .into(productImage!!)
        }

    }


}