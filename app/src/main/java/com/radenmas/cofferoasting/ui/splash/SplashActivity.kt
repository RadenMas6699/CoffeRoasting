/*
 * Created by RadenMas on 25/8/2022.
 * Copyright (c) 2022.
 */

package com.radenmas.cofferoasting.ui.splash

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.radenmas.cofferoasting.R
import com.radenmas.cofferoasting.databinding.ActivitySplashBinding
import com.radenmas.cofferoasting.ui.main.MainActivity

/**
 * Created by RadenMas on 24/08/2022.
 */

class SplashActivity : AppCompatActivity() {

    lateinit var b: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
        b = ActivitySplashBinding.inflate(layoutInflater)
        val view = b.root
        setContentView(view)

        initView()
        onClick()
    }

    private fun onClick() {

    }

    private fun initView() {
        val versionName = packageManager.getPackageInfo(packageName, 0).versionName
        b.tvAppVersion.text = resources.getString(R.string.app_version, versionName)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 1500)
    }
}