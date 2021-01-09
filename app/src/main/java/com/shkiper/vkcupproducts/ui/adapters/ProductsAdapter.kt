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


class ProductsAdapter(private val products: List<Product>, private val listener: (Product) -> Unit) : RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.rv_product_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]
        holder.bind(product, listener)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var productTitle: TextView? = null
        private var productImage: ImageView? = null
        private var productPrice: TextView? = null

        init {
            productTitle = itemView.findViewById(R.id.tv_title_of_product_item)
            productPrice = itemView.findViewById(R.id.tv_price_product_item)
            productImage = itemView.findViewById(R.id.iv_product_image_item)
        }
        fun bind(product: Product, listener: (Product) -> Unit){
            productTitle!!.text = product.title
            productPrice!!.text = product.price

            Glide.with(itemView)
                    .load(product.imagePath)
                    .into(productImage!!)


            itemView.setOnClickListener{listener.invoke(product)}
        }



    }


}