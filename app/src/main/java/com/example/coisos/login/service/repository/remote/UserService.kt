package com.example.coisos.login.service.repository.remote

import com.example.coisos.login.service.model.UserModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.POST

interface UserService {

    @POST("users/login")
    fun userLogin(
        @Body requestBody: UserModel
    ): Call<UserModel>

    @POST("users/register")
    fun userRegister(
        @Field("name") name: String,
        @Field("identifier") identifier: String,
        @Field("password") password: String
    ): Call<UserModel>
}