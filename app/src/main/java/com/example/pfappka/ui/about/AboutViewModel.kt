package com.example.pfappka.ui.about

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AboutViewModel : ViewModel() {

    private val _name = MutableLiveData<String>().apply {
        value = ""
    }
    val name: LiveData<String> = _name

    private val _weight = MutableLiveData<String>().apply {
        value = ""
    }
    val weight: LiveData<String> = _weight

    private val _height = MutableLiveData<String>().apply {
        value = ""
    }
    val height: LiveData<String> = _height

    private val _bmi = MutableLiveData<String>().apply {
        value = ""
    }
    val bmi: LiveData<String> = _bmi



    fun setBMI(bmi: String) {
        _bmi.value = bmi
    }

    fun setValuesMinusBMI(name: String, height: String, weight: String) {
        _name.value = name
        _height.value = height
        _weight.value = weight
    }

    fun setStringsFromSharedPreferences(sharedPreferences: SharedPreferences) {
        val newValue1 = sharedPreferences.getString("name", "User")
        _name.value = newValue1

        val newValue2 = sharedPreferences.getString("weight", "")
        _weight.value = newValue2

        val newValue3 = sharedPreferences.getString("height", "")
        _height.value = newValue3

        val newValue4 = sharedPreferences.getString("bmi", "")
        _bmi.value = newValue4
    }


}
