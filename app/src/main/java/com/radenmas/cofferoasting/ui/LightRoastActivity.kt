/*
 * Created by RadenMas on 13/9/2022.
 * Copyright (c) 2022.
 */

package com.radenmas.cofferoasting.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.radenmas.cofferoasting.databinding.ActivityLightRoastBinding

class LightRoastActivity : AppCompatActivity() {

    lateinit var b: ActivityLightRoastBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityLightRoastBinding.inflate(layoutInflater)
        val view = b.root
        setContentView(view)

        initView()

        onClick()
    }

    private fun onClick() {

    }

    private fun initView() {
        b.speedViewLight.speedTo(156F)

        /*
        Get Data Suhu
        Jika telah mencapai suhu maksimum
        tampilkan notifikasi
        lalu gas akan mati
        */

    }
}