package com.example.coisos.login.service.listener

import com.example.coisos.login.service.model.UserModel

interface ApiListener<T> {
    fun onSuccess(result: T)
    fun onFailure(message: String?)
}