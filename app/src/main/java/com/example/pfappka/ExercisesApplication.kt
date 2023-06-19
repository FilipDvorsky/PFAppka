package com.example.pfappka

import android.app.Application
import com.example.pfappka.db.ExercisesDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class ExercisesApplication : Application() {

    val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { ExercisesDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { ExercisesRepository(database.exercisesDao()) }
}
