package com.example.pfappka

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData

class ExercisesViewModel (val repository: ExercisesRepository) : ViewModel() {

    val exercises = repository.exercises.asLiveData()

    class ExercisesViewModelFactory(private val repository: ExercisesRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ExercisesViewModel::class.java)) {
                return ExercisesViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }

    }


}
