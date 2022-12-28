/*
 * Created by RadenMas on 13/9/2022.
 * Copyright (c) 2022.
 */

package com.radenmas.cofferoasting.ui

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.firebase.messaging.FirebaseMessaging
import com.radenmas.cofferoasting.databinding.ActivityLightRoastBinding

class DarkRoastActivity : AppCompatActivity() {

    lateinit var b: ActivityLightRoastBinding

    private var requestQueue: RequestQueue? = null
    private var stringData: StringRequest? = null

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

        FirebaseMessaging.getInstance().subscribeToTopic("user")

        requestQueue = Volley.newRequestQueue(this@DarkRoastActivity)

        val handler = Handler()
        val task: Runnable = object : Runnable {
            override fun run() {
                handler.postDelayed(this, 1000)
                stringData = StringRequest(
                    Request.Method.GET,
                    "http://192.168.4.1/",
                    { response ->
                        val temp = response.toString().toFloat()
                        b.speedViewLight.speedTo(temp)
                    },
                    {})
                requestQueue?.add(stringData)
            }
        }
        handler.post(task)


        /*
        Get Data Suhu
        Jika telah mencapai suhu maksimum
        tampilkan notifikasi
        lalu gas akan mati
        */

    }
}