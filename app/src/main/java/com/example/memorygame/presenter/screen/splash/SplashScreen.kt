package com.example.memorygame.presenter.screen.splash

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.memorygame.R
import com.example.memorygame.presenter.screen.home.HomeScreen
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class SplashScreen: Fragment(R.layout.screen_splash) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            Log.d("TTT", "after 2 secund")
            delay(2000)
            Log.d("TTT", "before 2 secund")
           parentFragmentManager
               .beginTransaction()
               .replace(R.id.container, HomeScreen())
               .commit()
        }


    }
}