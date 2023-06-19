package com.example.pfappka.db

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Entity(tableName = "exercises")
@Parcelize
data class Exercise(
    @PrimaryKey val id: Int,
    val lok: String,
    val name: String,
    val desc: String,
    val descLong: String
) : Parcelable