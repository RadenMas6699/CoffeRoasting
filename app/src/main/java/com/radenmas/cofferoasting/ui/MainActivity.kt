/*
 * Created by RadenMas on 13/9/2022.
 * Copyright (c) 2022.
 */

package com.radenmas.cofferoasting.ui

import android.content.BroadcastReceiver
import android.content.Context
import android.os.CountDownTimer
import android.os.Handler
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.radenmas.cofferoasting.R
import com.radenmas.cofferoasting.databinding.ActivityLightRoastBinding
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.messaging.FirebaseMessaging
import com.radenmas.cofferoasting.databinding.ActivityMainBinding
import com.radenmas.cofferoasting.service.TimerService

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var timerStarted = false
    private lateinit var serviceIntent: Intent
    private var time = 0.0

    private var roast: Boolean = false
    private var statusRoast: Int = 0

    private val database = FirebaseDatabase.getInstance().reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initView()

        database.addValueEventListener(object : ValueEventListener {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onDataChange(snapshot: DataSnapshot) {
                when (snapshot.child("status").value.toString().toInt()) {
                    0 -> {
                        binding.speedViewLight.maxSpeed = 205F
                    }
                    1 -> {
                        binding.speedViewLight.maxSpeed = 205F
                    }
                    2 -> {
                        binding.speedViewLight.maxSpeed = 219F
                    }
                    3 -> {
                        binding.speedViewLight.maxSpeed = 225F
                    }
                }

                when (snapshot.child("roast").value) {
                    true -> {
                        roast = true
                        binding.btnStart.text = "Roasting"
                    }
                    false -> {
                        roast = false
                        binding.btnStart.text = "Mulai"
                    }
                }

                val temp = snapshot.child("temp").value.toString().toFloat()
                binding.speedViewLight.speedTo(temp)
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

        onClick()
    }

    private fun onClick() {
        binding.btnLightRoast.setOnFocusChangeListener { view, _ ->
            if (view.isFocused) {
                binding.speedViewLight.maxSpeed = 205F
                statusRoast = 1
                database.child("status").setValue(statusRoast)
            }
        }

        binding.btnMediumRoast.setOnFocusChangeListener { view, _ ->
            if (view.isFocused) {
                binding.speedViewLight.maxSpeed = 219F
                statusRoast = 2
                database.child("status").setValue(statusRoast)
            }
        }

        binding.btnDarkRoast.setOnFocusChangeListener { view, _ ->
            if (view.isFocused) {
                binding.speedViewLight.maxSpeed = 225F
                statusRoast = 3
                database.child("status").setValue(statusRoast)
            }
        }

        binding.btnStart.setOnClickListener {
            if (!roast && statusRoast == 0) {
                Toast.makeText(
                    this@MainActivity,
                    "Pilih jenis roast terlebih dahulu.",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                database.child("roast").setValue(true)
            }
        }
    }

    private fun initView() {
        FirebaseMessaging.getInstance().subscribeToTopic("user")
        serviceIntent = Intent(this, TimerService::class.java)
        registerReceiver(updateTime, IntentFilter(TimerService.TIMER_UPDATED))
    }

    private val updateTime: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            time = intent.getDoubleExtra(TimerService.TIME_EXTRA, 0.0)
//            binding.tvTime.text = getTimeStringFromDouble(time)
        }
    }

    private fun getTimeStringFromDouble(time: Double): String {
        val resultInt = time.roundToInt()
        val minutes = resultInt % 86400 % 3600 / 60
        val seconds = resultInt % 86400 % 3600 % 60

        return makeTimeString(minutes, seconds)
    }

    private fun makeTimeString(min: Int, sec: Int): String = String.format("%02d:%02d", min, sec)
}