package com.example.memorygame.presentation.viewmodel.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.memorygame.R
import com.example.memorygame.data.LevelEnum
import com.example.memorygame.domain.GameRepository
import com.example.memorygame.navigation.AppNavigator
import com.example.memorygame.presentation.viewmodel.GameViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModelImpl @Inject constructor(
    private val repository: GameRepository,
    private val appNavigator: AppNavigator
) : ViewModel(), GameViewModel {
    override val cardsList = MutableStateFlow<List<Int>>(arrayListOf())

    override fun openGameScreen(level: LevelEnum) {
        repository.getCardsByLevel(level = level)
            .onEach { cardsList.value = it }
            .launchIn(viewModelScope)
    }

    override fun onClickMenu() {
        viewModelScope.launch {
            appNavigator.navigateUp()
        }
    }

    override fun onClickRefresh() {

    }

}