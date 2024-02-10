package com.example.memorygame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.memorygame.presenter.screen.splash.SplashScreen


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

      supportFragmentManager
          .beginTransaction()
          .replace(R.id.container, SplashScreen())
          .commit()


    }
}