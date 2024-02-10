package com.example.memorygame.presenter.screen.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.memorygame.R
import com.example.memorygame.databinding.ScreenLevelBinding
import com.example.memorygame.presenter.screen.game.GameScreen

class HomeScreen: Fragment(R.layout.screen_level) {
    private val binding by viewBinding(ScreenLevelBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.easy.setOnClickListener{
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, GameScreen())
                .addToBackStack("")
                .commit()
        }
        binding.medium.setOnClickListener{
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, GameScreen())
                .addToBackStack("")
                .commit()
        }
        binding.hard.setOnClickListener{
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, GameScreen())
                .addToBackStack("")
                .commit()
        }
    }
}