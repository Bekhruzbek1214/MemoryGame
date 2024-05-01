package com.example.memorygame.presentation.viewmodel

import com.example.memorygame.data.LevelEnum
import kotlinx.coroutines.flow.StateFlow

interface GameViewModel {
    val cardsList : StateFlow<List<Int>>


    fun openGameScreen(level: LevelEnum)

    fun onClickMenu()

    fun onClickRefresh()
}