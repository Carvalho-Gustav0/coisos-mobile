package com.example.coisos.login.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.coisos.login.service.listener.ApiListener
import com.example.coisos.login.service.model.UserModel
import com.example.coisos.login.service.repository.UserRepository

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val _messageLogin = MutableLiveData<String>()
    val messageLogin = _messageLogin

    private val userRepository = UserRepository()

    fun doLogin(identifier: String, password: String) {
        userRepository.login(
            UserModel(identifier = identifier, password = password),
            object : ApiListener<UserModel> {
                override fun onSuccess(result: UserModel) {
                    _messageLogin.value = result.message
                }

                override fun onFailure(message: String?) {
                    message?.let { _messageLogin.value = it }
                }

            })
    }
}