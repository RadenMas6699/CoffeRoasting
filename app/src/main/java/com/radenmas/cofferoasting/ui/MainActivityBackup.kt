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
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.messaging.FirebaseMessaging
import com.radenmas.cofferoasting.databinding.ActivityMainBackupBinding
import com.radenmas.cofferoasting.databinding.ActivityMainBinding
import com.radenmas.cofferoasting.service.TimerService

class MainActivityBackup : AppCompatActivity() {

    private lateinit var binding: ActivityMainBackupBinding

    private var timerStarted = false
    private lateinit var serviceIntent: Intent
    private var time = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBackupBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initView()


        onClick()
    }

    private fun onClick() {



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