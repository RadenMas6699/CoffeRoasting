/*
 * Created by RadenMas on 13/9/2022.
 * Copyright (c) 2022.
 */

package com.radenmas.cofferoasting.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.radenmas.cofferoasting.R
import com.radenmas.cofferoasting.databinding.ActivityDarkRoastBinding
import com.radenmas.cofferoasting.databinding.ActivityLightRoastBinding

class DarkRoastActivity : AppCompatActivity() {

    lateinit var b: ActivityDarkRoastBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityDarkRoastBinding.inflate(layoutInflater)
        val view = b.root
        setContentView(view)

        initView()

        onClick()
    }

    private fun onClick() {

    }

    private fun initView() {

    }
}