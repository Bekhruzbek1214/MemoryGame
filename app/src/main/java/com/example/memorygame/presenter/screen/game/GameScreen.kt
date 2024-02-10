package com.example.memorygame.presenter.screen.game

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.memorygame.R
import com.example.memorygame.databinding.ScreenGameBinding

class GameScreen : Fragment(R.layout.screen_game) {
    private val binding by viewBinding(ScreenGameBinding::bind)
    private val x = 4
    private val y = 6
    private val views = ArrayList<ImageView>()
    private var cardHeight = 0f
    private var cardWidth = 0f
    private var clickCounts = 0
    private var firstClickIndex = -1
    private var secondClickIndex = -1
    val back = R.drawable.image_back
    private val images = arrayListOf(
        Triple(0, R.drawable.image_1, back),
        Triple(1, R.drawable.image_1, back),
        Triple(2, R.drawable.image_1, back),
        Triple(3, R.drawable.image_2, back),
        Triple(4, R.drawable.image_2, back),
        Triple(5, R.drawable.image_2, back),
        Triple(6, R.drawable.image_2, back),
        Triple(7, R.drawable.image_2, back),
        Triple(8, R.drawable.image_2, back),
        Triple(9, R.drawable.image_2, back),
        Triple(10, R.drawable.image_2, back),
        Triple(11, R.drawable.image_2, back),
        Triple(12, R.drawable.image_2, back),
        Triple(13, R.drawable.image_2, back),
        Triple(14, R.drawable.image_2, back),
        Triple(15, R.drawable.image_2, back),
        Triple(16, R.drawable.image_2, back),
        Triple(17, R.drawable.image_2, back),
        Triple(18, R.drawable.image_2, back),
        Triple(19, R.drawable.image_2, back),
        Triple(20, R.drawable.image_2, back),
        Triple(21, R.drawable.image_2, back),
        Triple(22, R.drawable.image_2, back),
        Triple(23, R.drawable.image_2, back),
        Triple(24, R.drawable.image_2, back)

    )

    private val ROTATION_ANGLE = 89f
    private val ANIMATION_DURATION = 1000L

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.container.post {
            cardWidth = binding.container.width.toFloat() / x
            cardHeight = binding.container.height.toFloat() / y

            addImages()
        }
    }

    private fun addImages() {
        for (i in 0 until x) {
            for (j in 0 until y) {
                var temp = 0;
                val img = ImageView(requireContext())
                img.x = i * cardWidth
                img.y = j * cardHeight

                img.setImageResource(images[temp++].third)
                views.add(img)

                binding.container.addView(img)
                img.layoutParams.apply {
                    height = cardHeight.toInt()
                    width = cardWidth.toInt()
                }

                img.setOnClickListener{
                    clickImage(images[view?.id!!].second, images[view?.id!!])
                }


            }
        }
    }

    private fun clickImage(imageView: Int, triple: Triple<Int, Int, Int>) {
        triple[]
    }


//    private fun onImageClicked(imageView: ImageView, imageResource: Int) {
//        val index = views.indexOf(imageView)
//        if (index == -1) return
//        if (imageView.rotationY != 0f) return
//
//        if (clickCounts == 0) {
//            firstClickIndex = index
//
//        imageView.animate()
//            .setDuration(ANIMATION_DURATION)
//            .rotationY(ROTATION_ANGLE)
//            .withEndAction {
//                imageView.rotationY = -ROTATION_ANGLE
//                imageView.setImageResource(imageResource)
//                imageView.animate()
//                    .setDuration(ANIMATION_DURATION)
//                    .rotationY(0f)
//                    .start()
//            }
//            .start()
//        } else if (clickCounts == 1) {
//            secondClickIndex = index
//            clickCounts++
//            imageView.animate()
//                .setDuration(ANIMATION_DURATION)
//                .rotationY(ROTATION_ANGLE)
//                .withEndAction {
//                    imageView.rotationY = -ROTATION_ANGLE
//                    imageView.setImageResource(R.drawable.image_back)
//                    imageView.animate()
//                        .setDuration(ANIMATION_DURATION)
//                        .rotationY(0f)
//                        .start()
//                }
//                .start()
//
//        }
//    }
}




