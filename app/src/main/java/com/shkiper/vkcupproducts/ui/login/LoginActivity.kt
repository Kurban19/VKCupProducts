package com.shkiper.vkcupproducts.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.shkiper.vkcupproducts.R
import com.shkiper.vkcupproducts.ui.main.MainActivity
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKAccessToken
import com.vk.api.sdk.auth.VKAuthCallback
import com.vk.api.sdk.auth.VKScope
import com.vk.api.sdk.utils.VKUtils


class LoginActivity : AppCompatActivity() {


    companion object{
        fun startFrom(context: Context){
            val intent = Intent(context, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            context.startActivity(intent)
        }
    }

    private lateinit var btnLogin: Button
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

//        val fingerprints = VKUtils.getCertificateFingerprint(this, this.packageName)

//        Log.d("Tag", fingerprints!![0].toString())
//        print(fingerprints[0].toString())

        if (VK.isLoggedIn()) {
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
            return
        }

        btnLogin = findViewById(R.id.btn_login)
        progressBar = findViewById(R.id.progress_bar)

        btnLogin.setOnClickListener {
            VK.login(this, arrayListOf(VKScope.GROUPS))
            progressBar.visibility = ProgressBar.VISIBLE
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val callback = object: VKAuthCallback {
            override fun onLogin(token: VKAccessToken) {
                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }

            override fun onLoginFailed(errorCode: Int) {
                Toast.makeText(this@LoginActivity, "Что-то пошло не так", Toast.LENGTH_LONG).show()
                progressBar.visibility = ProgressBar.GONE
            }
        }
        if (data == null || !VK.onActivityResult(requestCode, resultCode, data, callback)) {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}