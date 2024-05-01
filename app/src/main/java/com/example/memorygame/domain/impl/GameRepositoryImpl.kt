package com.example.memorygame.domain.impl

import com.example.memorygame.R
import com.example.memorygame.data.LevelEnum
import com.example.memorygame.domain.GameRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class GameRepositoryImpl @Inject constructor() : GameRepository {
    private val data = ArrayList<Int>()
    init {
        loadData()
    }
    override fun getCardsByLevel(level: LevelEnum): Flow<List<Int>> = flow {
        val cards = data.shuffled().subList(0, (level.column * level.row) / 2).toMutableList()
        cards.addAll(cards)
        emit(cards.shuffled())
    }
    private fun loadData() {
        data.apply {
            add(R.drawable.vintage1_bg)
            add(R.drawable.vintage2_bg)
            add(R.drawable.vintage3_bg)
            add(R.drawable.vintage4_bg)
            add(R.drawable.vintage5_bg)
            add(R.drawable.vintage6_bg)
            add(R.drawable.vinatage7_bg)
            add(R.drawable.vintage8_bg)
            add(R.drawable.vintage9_bg)
            add(R.drawable.vintage10_bg)
            add(R.drawable.vintage11_bg)
            add(R.drawable.vintage12_bg)
            add(R.drawable.vintage14_bg)
            add(R.drawable.vintage15_bg)
            add(R.drawable.vintage16_bg)
            add(R.drawable.vintage17_bg)
            add(R.drawable.vintage18_bg)
            add(R.drawable.vintage19_bg)
            add(R.drawable.vintage20_bg)
            add(R.drawable.vintage21_bg)
            add(R.drawable.vintage22_bg)
            add(R.drawable.vintage23_bg)
            add(R.drawable.vintage24_bg)
            add(R.drawable.vinatage13_bg)
        }
    }
}