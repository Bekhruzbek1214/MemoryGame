package com.example.memorygame.presentation.screen

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.memorygame.R
import com.example.memorygame.databinding.ScreenMenuBinding
import com.example.memorygame.presentation.viewmodel.MenuViewModel
import com.example.memorygame.presentation.viewmodel.impl.MenuVIewModelImpl
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import ru.ldralighieri.corbind.view.clicks

@AndroidEntryPoint
class MenuScreen : Fragment(R.layout.screen_menu) {
    private val binding by viewBinding(ScreenMenuBinding::bind)
    private val viewModel: MenuViewModel by viewModels<MenuVIewModelImpl>()
    private var time = System.currentTimeMillis()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val window = requireActivity().window
        val wicc = WindowInsetsControllerCompat(window, window.decorView)
        window.statusBarColor = Color.parseColor("#FFFFFF")
        wicc.isAppearanceLightStatusBars = true

        initFLow()
    }


    private fun initFLow() = binding.apply {
        btnEasy.setOnClickListener {
            if (System.currentTimeMillis() - time > 1000) {
                time = System.currentTimeMillis()
                viewModel.onClickEasy()
            }
        }

        btnMedium
            .clicks()
            .onEach {
                if (System.currentTimeMillis() - time > 1000) {
                    time = System.currentTimeMillis()
                    viewModel.onClickMedium()
                }
            }
            .flowWithLifecycle(lifecycle)
            .launchIn(lifecycleScope)

        btnHard
            .clicks()
            .onEach {
                if (System.currentTimeMillis() - time > 1000) {
                    time = System.currentTimeMillis()
                    viewModel.onClickHard()
                }
            }
            .flowWithLifecycle(lifecycle)
            .launchIn(lifecycleScope)
    }
}