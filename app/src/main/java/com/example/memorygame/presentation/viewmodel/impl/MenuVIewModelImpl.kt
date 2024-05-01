package com.example.memorygame.presentation.viewmodel.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.memorygame.data.LevelEnum
import com.example.memorygame.navigation.AppNavigator
import com.example.memorygame.presentation.screen.MenuScreenDirections
import com.example.memorygame.presentation.viewmodel.MenuViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MenuVIewModelImpl @Inject constructor(
    private val appNavigator: AppNavigator
) : ViewModel(), MenuViewModel{

    override fun onClickEasy() {
        navigateToGameScreen(LevelEnum.EASY)
    }

    override fun onClickMedium() {
        navigateToGameScreen(LevelEnum.MEDIUM)
    }

    override fun onClickHard() {
        navigateToGameScreen(LevelEnum.HARD)
    }

    private fun navigateToGameScreen(levelEnum: LevelEnum) {
        viewModelScope.launch {
            appNavigator.navigateTo(MenuScreenDirections.actionMenuScreenToGameScreen(level = levelEnum))
        }
    }
}