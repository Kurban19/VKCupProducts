package com.shkiper.vkcupproducts.util
import android.app.Application
import com.vk.api.sdk.VK

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        VK.initialize(applicationContext)
    }
}