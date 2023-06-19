package com.example.pfappka

import com.example.pfappka.db.ExercisesDao
import java.util.*

class ExercisesRepository(private val exercisesDao: ExercisesDao) {

    val cur = Locale.getDefault().displayLanguage

    val exercises = exercisesDao.getAllExercises(cur)

}