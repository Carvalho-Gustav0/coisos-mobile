package com.example.coisos.login.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.coisos.login.service.listener.ApiListener
import com.example.coisos.login.service.model.UserModel
import com.example.coisos.login.service.repository.UserRepository

class LoginViewModel : ViewModel() {

    private val _isLogged = MutableLiveData(false)
    val isLogged = _isLogged

    private val _messageLogin = MutableLiveData<String>()
    val messageLogin = _messageLogin

    private val userRepository = UserRepository()

    fun doLogin(email: String, password: String) {
        userRepository.login(
            UserModel(email = email, password = password),
            object : ApiListener<UserModel> {
                override fun onSuccess(result: UserModel) {
                    _isLogged.value = true
                }

                override fun onFailure(message: String?) {
                    message?.let { _messageLogin.value = it }
                    _isLogged.value = false
                }
            })
    }
}