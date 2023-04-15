package com.example.coisos.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FileRpgViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is file rpg Fragment"
    }
    val text: LiveData<String> = _text
}