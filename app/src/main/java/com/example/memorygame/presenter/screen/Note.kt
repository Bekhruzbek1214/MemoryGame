package com.example.memorygame.presenter.screen

//private fun addClickEvent(list: List<Int>) {
//    images.forEach { image ->
//        image.setOnClickListener {
//            if (image.rotationY != 0f) return@setOnClickListener
//            if (click[images.indexOf(image)] && clickedCards != 2) {
//                if (clickedCards == 0) firstClick = images.indexOf(image)
//                if (clickedCards == 1) secondClick = images.indexOf(image)
//                image.animate()
//                    .setDuration(1000)
//                    .rotationY(89f)
//                    .withEndAction {
//                        image.rotationY = -89f
//                        image.setImageResource(list[images.indexOf(image)])
//                        image.animate()
//                            .setDuration(1000)
//                            .rotationY(0f)
//                            .withEndAction {
//                                if (firstClick != -1 && secondClick != -1 && list[firstClick] == list[secondClick]) {
//                                    images[firstClick].isClickable = false
//                                    images[secondClick].isClickable = false
//
//                                    repeat(images.size) {
//                                        if (firstClick != it && secondClick != it) {
//                                            images[it].isClickable = true
//                                        }
//                                    }
//                                }
//                            }
//                            .start()
//                    }
//                    .start()
//                click[images.indexOf(image)] = false
//                clickedCards++
//            } else if (!click[images.indexOf(image)]) {
//                if (clickedCards == 1) firstClick = -1
//                if (clickedCards == 2) secondClick = -1
//                image.animate()
//                    .setDuration(1000)
//                    .rotationY(-89f)
//                    .withEndAction {
//                        image.rotationY = 89f
//                        image.setImageResource(R.drawable.image_back)
//                        image.animate()
//                            .setDuration(1000)
//                            .rotationY(0f)
//                            .start()
//                    }
//                    .start()
//                click[images.indexOf(image)] = true
//                clickedCards--
//            }
//        }
//    }
//}