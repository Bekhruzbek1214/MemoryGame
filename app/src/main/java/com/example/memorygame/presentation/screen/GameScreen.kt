package com.example.memorygame.presentation.screen

import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.GridLayout
import android.widget.ImageView
import androidx.activity.OnBackPressedCallback
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.memorygame.R
import com.example.memorygame.data.LevelEnum
import com.example.memorygame.databinding.ScreenGameBinding
import com.example.memorygame.presentation.viewmodel.GameViewModel
import com.example.memorygame.presentation.viewmodel.impl.GameViewModelImpl
import com.example.memorygame.utils.closeAnim
import com.example.memorygame.utils.openAnim
import com.example.memorygame.utils.removeAnim
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import ru.ldralighieri.corbind.view.clicks
import kotlin.time.times

@AndroidEntryPoint
class GameScreen : Fragment(R.layout.screen_game) {
    private val viewModel: GameViewModel by viewModels<GameViewModelImpl>()
    private val binding by viewBinding(ScreenGameBinding::bind)
    private val cards = ArrayList<ImageView>()
    private var firstOpenIndex = -1
    private var secondOpenIndex = -1
    private var findCount = 0
    private val navArgs: GameScreenArgs by navArgs()
    private var countdownTimer: CountDownTimer? = null
    private var timeN = 1



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val window = requireActivity().window
        val wicc = WindowInsetsControllerCompat(window, window.decorView)
        window.statusBarColor = Color.parseColor("#FFFFFF")
        wicc.isAppearanceLightStatusBars = true

        binding.cardContainer.post {
            viewModel.openGameScreen(navArgs.level)
            if(navArgs.level == LevelEnum.HARD){
                timeN = 3
                Log.d("TTT", "LevelEnum.HARD: ${timeN}")
            }
            if(navArgs.level == LevelEnum.MEDIUM){

                timeN = 2
                Log.d("TTT", "LevelEnum.MEDIUM: ${timeN}")
            }
            Log.d("TTT", "time: ${timeN}")
            initView()
            initTime()
        }
        initFlow()


        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                countdownTimer?.cancel()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }

    private fun initTime(){
        Log.d("TTT" ,"Time: ${timeN}")
        countdownTimer =  object : CountDownTimer(timeN.toLong()*60000, 1000){
            override fun onTick(millisUntilFinished: Long) {
                val totalSeconds = millisUntilFinished/1000
                val minute = totalSeconds / 60
                val second = totalSeconds % 60
                binding.time.text = String.format("%02d:%02d", minute, second)
            }

            override fun onFinish() {
                if (findCount != navArgs.level.row * navArgs.level.column) {
                    val dialog = LoseDialog()
                    dialog.isCancelable = false
                    dialog.setOnClick {
                        dialog.dismiss()
                        viewModel.onClickMenu()
                    }
                    dialog.show(childFragmentManager, "")
                }
            }

        }.start()
    }

    private fun initFlow() = binding.apply {
        btnMenu
            .clicks()
            .onEach {
                viewModel.onClickMenu()
                countdownTimer?.cancel()
            }
            .flowWithLifecycle(lifecycle)
            .launchIn(lifecycleScope)


        viewModel.cardsList
            .onEach { loadCards(it) }
            .flowWithLifecycle(lifecycle)
            .launchIn(lifecycleScope)
    }

    private fun initView() = binding.apply {

    }

    private fun loadCards(list: List<Int>) = binding.apply {
        val containerWidth = cardContainer.width
        val containerHeight = cardContainer.height
        val rowCount = navArgs.level.row
        val columnCount = navArgs.level.column
        cardContainer.rowCount = rowCount
        cardContainer.columnCount = columnCount
        for (i in list.indices) {
            val img = ImageView(requireContext())
            img.setImageResource(R.drawable.question_mark_bg)
            img.setBackgroundResource(R.drawable.bg_card)
            cardContainer.addView(img)
            cards.add(img)
            (img.layoutParams as GridLayout.LayoutParams).apply {
                this.width = containerWidth / columnCount - 10
                this.height = containerHeight / rowCount - 10
                setMargins(0, 10, 10, 0)
            }
            img.isClickable = false
            img.openAnim { }
            img.tag = Pair(false, list[i])
            img.setOnClickListener {
                if (firstOpenIndex != -1 && secondOpenIndex != -1) return@setOnClickListener
                onCLickImg(img, i)
            }
        }
        lifecycleScope.launch {
            delay(1500)
            cards.forEach { it.closeAnim { } }
        }
    }



    private fun onCLickImg(img: ImageView, i: Int) {
        val tag = img.tag as Pair<Boolean, Int>
        if (tag.first) {

        } else {
            img.openAnim {}
            if (firstOpenIndex == -1) {
                firstOpenIndex = i
            } else {
                secondOpenIndex = i
                lifecycleScope.launch {
                    delay(1500)
                    if (!checkCorrect()) {
                        cards[firstOpenIndex].closeAnim { }
                        cards[secondOpenIndex].closeAnim {
                            firstOpenIndex = -1
                            secondOpenIndex = -1
                        }
                    }
                }
            }
        }
    }

    private fun checkCorrect(): Boolean {
        if (firstOpenIndex == -1 || secondOpenIndex == -1) return false
        val tag1 = cards[firstOpenIndex].tag as Pair<Boolean, Int>
        val tag2 = cards[secondOpenIndex].tag as Pair<Boolean, Int>
        if (tag1.second == tag2.second) {
            cards[firstOpenIndex].removeAnim { }
            cards[secondOpenIndex].removeAnim {
                firstOpenIndex = -1
                secondOpenIndex = -1
                findCount += 2
                checkWin()
            }
            return true
        }
        return false
    }

    private fun checkWin() {
        if (findCount == navArgs.level.row * navArgs.level.column) {
            countdownTimer?.cancel()
            val dialog = WinDialog()
            dialog.isCancelable = false
            dialog.setOnClick {
                dialog.dismiss()
                viewModel.onClickMenu()
            }
            dialog.show(childFragmentManager, "")
        }
    }

}