/*
 * Created by RadenMas on 25/8/2022.
 * Copyright (c) 2022.
 */

package com.radenmas.cofferoasting.ui.main

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.radenmas.cofferoasting.databinding.ActivityLightroastBinding
import com.radenmas.cofferoasting.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var b: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
        b = ActivityMainBinding.inflate(layoutInflater)
        val view = b.root
        setContentView(view)

        initView()


        onClick()
    }

    private fun onClick() {
         b.btnlightroast.setOnClickListener {
             startActivity(Intent(this,applicationInfo ::class.java))
             finish()
         }

    }

    private fun initView() {

    }
}