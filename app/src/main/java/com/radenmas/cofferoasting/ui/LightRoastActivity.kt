/*
 * Created by RadenMas on 13/9/2022.
 * Copyright (c) 2022.
 */

package com.radenmas.cofferoasting.ui

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.firebase.messaging.FirebaseMessaging
import com.radenmas.cofferoasting.R
import com.radenmas.cofferoasting.databinding.ActivityLightRoastBinding
import com.radenmas.cofferoasting.service.TimerService
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

class LightRoastActivity : AppCompatActivity() {

    lateinit var b: ActivityLightRoastBinding

    private var requestQueue: RequestQueue? = null
    private var stringData: StringRequest? = null

    private var timerStarted = false
    private lateinit var serviceIntent: Intent
    private var time = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityLightRoastBinding.inflate(layoutInflater)
        val view = b.root
        setContentView(view)

        serviceIntent = Intent(this, TimerService::class.java)
        registerReceiver(updateTime, IntentFilter(TimerService.TIMER_UPDATED))

        initView()
        onClick()
    }

    private fun onClick() {
        b.btnStart.setOnClickListener {
            val timer = object : CountDownTimer(960000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    b.tvTime.text = formatClockSimple(millisUntilFinished)
                }

                override fun onFinish() {
                    Toast.makeText(this@LightRoastActivity, "DONE", Toast.LENGTH_SHORT).show()
                }
            }
            timer.start()

//            if (timerStarted) {
//                stopTimer()
//            } else {
//                startTimer()
//            }
        }
    }

    private fun initView() {

        FirebaseMessaging.getInstance().subscribeToTopic("user")

        requestQueue = Volley.newRequestQueue(this@LightRoastActivity)

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

    private fun resetTimer() {
        stopTimer()
        time = 0.0
        b.tvTime.text = getTimeStringFromDouble(time)
    }

    private fun startTimer() {
        serviceIntent.putExtra(TimerService.TIME_EXTRA, time)
        startService(serviceIntent)
        b.btnStart.text = "Stop"
//        b.btnStart.icon = getDrawable(R.drawable.ic_pause)
//        timerStarted = true
    }

    private fun stopTimer() {
        stopService(serviceIntent)
        b.btnStart.text = "Start"
//        b.btnStart.icon = getDrawable(R.drawable.ic_play)
        timerStarted = false
    }

    private val updateTime: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            time = intent.getDoubleExtra(TimerService.TIME_EXTRA, 0.0)
            b.tvTime.text = getTimeStringFromDouble(time)
        }
    }

    private fun getTimeStringFromDouble(time: Double): String {
        val resultInt = time.roundToInt()
//        val hours = resultInt % 86400 / 3600
        val minutes = resultInt % 86400 % 3600 / 60
        val seconds = resultInt % 86400 % 3600 % 60

        return makeTimeString(minutes, seconds)
    }

    private fun makeTimeString(min: Int, sec: Int): String = String.format("%02d:%02d", min, sec)

    fun formatClockSimple(timestamp: Long): String {
        val formatDate = SimpleDateFormat(
            "mm:ss", Locale("ID")
        )
        val date = Date(timestamp)
        return formatDate.format(date)
    }
}