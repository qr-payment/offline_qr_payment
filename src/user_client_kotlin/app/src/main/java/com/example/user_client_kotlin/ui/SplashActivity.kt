package com.example.user_client_kotlin.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.user_client_kotlin.MainActivity
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.startActivity

class SplashActivity : AppCompatActivity(), AnkoLogger {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startActivity<MainActivity>()
        finish()
    }
}