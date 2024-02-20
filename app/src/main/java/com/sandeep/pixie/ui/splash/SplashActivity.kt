package com.sandeep.pixie.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.sandeep.pixie.adapter.MainAdapter
import com.sandeep.pixie.databinding.ActivitySplashBinding
import com.sandeep.pixie.ui.MainActivity
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Sandeep Pramanik on 20 February,2024.
 */
@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity: AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 5000)
    }
}