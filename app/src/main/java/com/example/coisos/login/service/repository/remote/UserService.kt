package com.example.coisos.login.service.repository.remote

import com.example.coisos.login.service.model.UserModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {

    @POST("user/login")
    fun userLogin(
        @Body requestBody: UserModel
    ): Call<UserModel>

    @POST("user/create")
    fun userRegister(
        @Body requestBody: UserModel
    ): Call<UserModel>
}