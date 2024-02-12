package com.example.memorygame.presenter.screen.game

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.setMargins
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.memorygame.R
import com.example.memorygame.data.CardData
import com.example.memorygame.data.LevelEnum
import com.example.memorygame.databinding.ScreenGameBinding
import com.example.memorygame.presenter.screen.closeAnim
import com.example.memorygame.presenter.screen.game.viewmodelGame.GameViewModel
import com.example.memorygame.presenter.screen.game.viewmodelGame.GameViewModelImp
import com.example.memorygame.presenter.screen.hideAnim
import com.example.memorygame.presenter.screen.openFirstAnim
import com.example.memorygame.presenter.screen.openSecondAnim
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class GameScreen : Fragment(R.layout.screen_game) {
    private val binding by viewBinding(ScreenGameBinding::bind)
    private val viewModel: GameViewModel by viewModels<GameViewModelImp>()
    private val views = ArrayList<ImageView>()
    private var cardWidth = 0f
    private var cardHeight = 0f
    private var firstIndex = -1
    private var secondIndex = -1
    private var level = LevelEnum.EASY
    private var findCardCount = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        level = requireArguments().getSerializable("level") as LevelEnum
        binding.container.post {
            cardHeight = binding.container.height.toFloat() / level.verCount
            cardWidth = binding.container.width.toFloat() / level.horCount

            viewModel.loadCardByLevel(level)
        }

        viewModel.cardFlow
            .onEach { loadViews(level, it)}
            .launchIn(lifecycleScope)

        viewModel.closeAllViewSFlow
            .onEach { closeAllViews() }
            .launchIn(lifecycleScope)
    }

    private fun loadViews(levelEnum: LevelEnum, ls: List<CardData>){
        for (i in 0 until levelEnum.horCount){
            for(j in 0 until levelEnum.verCount){
                val img = ImageView(requireContext())

                img.x = i * cardWidth
                img.y = j * cardHeight
                binding.container.addView(img)
                img.isClickable = false
                img.tag = ls[i*levelEnum.verCount + j]
                img.setImageResource(ls[i*levelEnum.verCount + j].resID)
                views.add(img)

                val lp = img.layoutParams as ViewGroup.MarginLayoutParams
                lp.apply {
                    lp.width = cardWidth.toInt()
                    lp.height = cardHeight.toInt()
                    lp.setMargins(50)
                }
                img.layoutParams = lp
            }
        }
        clickReaction()
    }

    private fun closeAllViews(){
        views.forEach{image ->
            image.closeAnim{
                image.isClickable = true
            }
        }
    }

    private fun clickReaction(){
        views.forEachIndexed { index, imageView ->
            imageView.setOnClickListener{
                if(firstIndex != -1 && secondIndex != -1) return@setOnClickListener
                if(index == firstIndex || index == secondIndex) return@setOnClickListener


                if(firstIndex == -1){
                    firstIndex = index
                    imageView.openFirstAnim()
                }else{
                    secondIndex = index
                    imageView.openSecondAnim {
                        check()
                    }
                }
            }
        }
    }

    private fun check(){
        val firstData = views[firstIndex].tag as CardData
        val secondData = views[secondIndex].tag as CardData

        if(firstData.id == secondData.id){
            views[firstIndex].hideAnim()
            views[secondIndex].hideAnim {
                firstIndex = -1
                secondIndex = -1
                findCardCount += 2
                isFinish()
            }
        }else{
            views[firstIndex].closeAnim()
            views[secondIndex].closeAnim {
                firstIndex = -1
                secondIndex = -1
            }
        }
    }
    private fun isFinish(){
        if(findCardCount == level.verCount * level.verCount){
            Toast.makeText(requireContext(), "FINISH", Toast.LENGTH_SHORT).show()
        }
    }


}














