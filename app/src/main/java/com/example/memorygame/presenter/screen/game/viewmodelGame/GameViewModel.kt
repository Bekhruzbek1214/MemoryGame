package com.example.memorygame.presenter.screen.game.viewmodelGame

import com.example.memorygame.data.CardData
import com.example.memorygame.data.LevelEnum
import kotlinx.coroutines.flow.SharedFlow

interface GameViewModel {
    val cardFlow: SharedFlow<List<CardData>>
    val closeAllViewSFlow: SharedFlow<Unit>

    fun loadCardByLevel(level: LevelEnum)
}