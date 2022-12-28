/*
 * Created by RadenMas on 13/9/2022.
 * Copyright (c) 2022.
 */

package com.radenmas.cofferoasting.ui

import android.content.Intent
import android.os.Bundle
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

        b.btnlightkroast.setOnClickListener {
            startActivity(Intent(this, LightRoastActivity::class.java))
        }

        b.btnmediumRoast.setOnClickListener {
            startActivity(Intent(this, MediumRoastActivity::class.java))
        }

        b.btndarkRoast.setOnClickListener {
            startActivity(Intent(this, DarkRoastActivity::class.java))
        }
        b.btnmanualRoast.setOnClickListener {
            startActivity(Intent(this, ManualRoastActivity::class.java))
        }
    }

    private fun initView() {

    }
}