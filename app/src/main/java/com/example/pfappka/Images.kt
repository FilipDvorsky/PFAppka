package com.example.pfappka

import java.util.Objects

class Images{
    private val imageIds = intArrayOf(
        R.drawable.exer1,
        R.drawable.exer2,
        R.drawable.exer3,
        R.drawable.exer4,
        R.drawable.exer5
    )
    fun getImageIds(id: Int): Int{
        return imageIds[(id - 1)%imageIds.size]
    }
}