/*
 * Created by RadenMas on 13/9/2022.
 * Copyright (c) 2022.
 */

package com.radenmas.cofferoasting.ui

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.radenmas.cofferoasting.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var b: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        val view = b.root
        setContentView(view)

        initView()


        onClick()
    }

    private fun onClick() {

        b.btnLightRoast.setOnClickListener {
            startActivity(Intent(this, LightRoastActivity::class.java))
        }

        b.btnMediumRoast.setOnClickListener {
            startActivity(Intent(this, MediumRoastActivity::class.java))
        }

        b.btnDarkRoast.setOnClickListener {
            startActivity(Intent(this, DarkRoastActivity::class.java))
        }
    }

    private fun initView() {

    }
}