package com.example.memorygame.domain

import com.example.memorygame.R
import com.example.memorygame.data.CardData
import com.example.memorygame.data.LevelEnum
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AppRepositoryImp @Inject constructor() : AppRepository {

    private val ls = ArrayList<CardData>()


    private fun loadData() {
        ls.add(CardData(1, R.drawable.image_1))
        ls.add(CardData(2, R.drawable.image_2))
        ls.add(CardData(3, R.drawable.image_3))
        ls.add(CardData(4, R.drawable.image_4))
        ls.add(CardData(5, R.drawable.image_5))
        ls.add(CardData(6, R.drawable.image_6))
        ls.add(CardData(7, R.drawable.image_7))
        ls.add(CardData(8, R.drawable.image_8))
        ls.add(CardData(9, R.drawable.image_9))
        ls.add(CardData(10, R.drawable.image_10))
        ls.add(CardData(11, R.drawable.image_11))
        ls.add(CardData(12, R.drawable.image_12))
        ls.add(CardData(13, R.drawable.image_13))
        ls.add(CardData(14, R.drawable.image_14))
        ls.add(CardData(15, R.drawable.image_15))
        ls.add(CardData(16, R.drawable.image_16))
        ls.add(CardData(17, R.drawable.image_17))
        ls.add(CardData(18, R.drawable.image_18))
        ls.add(CardData(19, R.drawable.image_19))
        ls.add(CardData(20, R.drawable.image_20))
        ls.add(CardData(21, R.drawable.image_21))
        ls.add(CardData(22, R.drawable.image_22))
        ls.add(CardData(23, R.drawable.image_23))
        ls.add(CardData(24, R.drawable.image_24))
        ls.add(CardData(25, R.drawable.image_25))
        ls.add(CardData(26, R.drawable.image_26))
        ls.add(CardData(27, R.drawable.image_27))
        //...
    }

    override fun getAllCardsByLevel(level: LevelEnum): Flow<List<CardData>> = flow {
        ls.shuffle()
        val count = level.horCount * level.verCount / 2
        val result = ArrayList<CardData>()
        result.addAll(ls.subList(0, count))
        result.addAll(ls.subList(0, count))
        result.shuffle()
        emit(result)
    }.flowOn(Dispatchers.IO)



}