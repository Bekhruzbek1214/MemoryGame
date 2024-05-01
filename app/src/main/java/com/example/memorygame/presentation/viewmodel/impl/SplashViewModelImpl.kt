package com.example.memorygame.presentation.viewmodel.impl

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.memorygame.navigation.AppNavigator
import com.example.memorygame.presentation.screen.SplashScreenDirections
import com.example.memorygame.presentation.viewmodel.SplashViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SplashViewModelImpl @Inject constructor(
    private val navigator: AppNavigator
) : ViewModel(), SplashViewModel {
    override fun openSplash() {
        viewModelScope.launch {
            delay(1500)
            navigator.navigateTo(SplashScreenDirections.actionSplashScreenToMenuScreen())
        }
    }
}