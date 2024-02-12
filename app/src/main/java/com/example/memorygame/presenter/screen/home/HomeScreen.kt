package com.example.memorygame.presenter.screen.home

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.memorygame.R
import com.example.memorygame.data.LevelEnum
import com.example.memorygame.databinding.ScreenLevelBinding
import com.example.memorygame.presenter.screen.game.GameScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeScreen: Fragment(R.layout.screen_level) {
    private val binding by viewBinding(ScreenLevelBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.easy.setOnClickListener{ findNavController().navigate(R.id.action_homeScreen_to_gameScreen, bundleOf("level" to LevelEnum.EASY))}
        binding.medium.setOnClickListener{ findNavController().navigate(R.id.action_homeScreen_to_gameScreen, bundleOf("level" to LevelEnum.MEDIUM))}
        binding.hard.setOnClickListener{ findNavController().navigate(R.id.action_homeScreen_to_gameScreen, bundleOf("level" to LevelEnum.HARD))}
    }

}