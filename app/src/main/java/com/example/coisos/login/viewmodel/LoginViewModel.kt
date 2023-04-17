package com.example.coisos.login.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.coisos.login.service.listener.ApiListener
import com.example.coisos.login.service.model.UserModel
import com.example.coisos.login.service.repository.UserRepository

class LoginViewModel : ViewModel() {

    private val _messageLogin = MutableLiveData<String>()
    val messageLogin = _messageLogin

    private val _isLogged = MutableLiveData(false)
    val isLogged = _isLogged

    private val userRepository = UserRepository()

    fun doLogin(identifier: String, password: String) {
        userRepository.login(
            UserModel(identifier = identifier, password = password),
            object : ApiListener<UserModel> {
                override fun onSuccess(result: UserModel) {
                    _messageLogin.value = result.message
                    _isLogged.value = true
                }

                override fun onFailure(message: String?) {
                    message?.let { _messageLogin.value = it }
                    _isLogged.value = false
                }
            })
    }
}