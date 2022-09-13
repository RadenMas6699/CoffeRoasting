package com.radenmas.cofferoasting.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.radenmas.cofferoasting.databinding.ActivityMediumRoastBinding

class MediumRoastActivity : AppCompatActivity() {

    lateinit var b: ActivityMediumRoastBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMediumRoastBinding.inflate(layoutInflater)
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