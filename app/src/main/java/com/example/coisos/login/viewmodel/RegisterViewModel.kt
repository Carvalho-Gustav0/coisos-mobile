package com.example.coisos.login.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.coisos.login.service.listener.ApiListener
import com.example.coisos.login.service.model.UserModel
import com.example.coisos.login.service.repository.UserRepository

class RegisterViewModel : ViewModel() {

    private val _messageRegister = MutableLiveData<String>()
    val messageRegister = _messageRegister

    private val _isRegistered = MutableLiveData<Boolean>()
    val isRegistered = _isRegistered

    private val userRepository = UserRepository()

    fun doRegister(name: String, identifier: String, password: String) {
        userRepository.register(
            UserModel(name = name, identifier = identifier, password = password),
            object : ApiListener<UserModel> {
                override fun onSuccess(result: UserModel) {
                    _messageRegister.value = result.message
                    _isRegistered.value = true
                }

                override fun onFailure(message: String?) {
                    message?.let { _messageRegister.value = it }
                    _isRegistered.value = false
                }
            })
    }
}