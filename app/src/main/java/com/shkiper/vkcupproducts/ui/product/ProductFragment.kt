package com.shkiper.vkcupproducts.ui.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.shkiper.vkcupproducts.R
import com.shkiper.vkcupproducts.models.Product

class ProductFragment: Fragment() {


    private lateinit var productImage : ImageView
    private lateinit var productTitle : TextView
    private lateinit var productPrice : TextView
    private lateinit var productDescription : TextView

    companion object{
        const val PRODUCT = "PRODUCT"
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val product = arguments?.getParcelable<Product>(PRODUCT) ?: throw KotlinNullPointerException()

        val view = inflater.inflate(R.layout.fragment_product, container, false)

        productImage = view.findViewById(R.id.iv_image_of_product)
        productTitle = view.findViewById(R.id.tv_title_of_product)
        productPrice = view.findViewById(R.id.tv_price_of_product)
        productDescription = view.findViewById(R.id.tv_description_of_product)

        Glide.with(view).load(product.imagePath).into(productImage)
        productTitle.text = product.title
        productPrice.text = product.price
        productDescription.text = product.description
        return view
    }




}