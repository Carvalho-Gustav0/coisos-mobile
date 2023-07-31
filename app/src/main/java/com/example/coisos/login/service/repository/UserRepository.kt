package com.example.coisos.login.service.repository

import com.example.coisos.login.service.listener.ApiListener
import com.example.coisos.login.service.model.ErrorResponseLogin
import com.example.coisos.login.service.model.ErrorResponseRegister
import com.example.coisos.login.service.model.UserModel
import com.example.coisos.login.service.repository.remote.UserService
import com.example.coisos.repository.remote.RetrofitClient
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UserRepository {

    private val service = RetrofitClient.getService(UserService::class.java)

    fun login(userModel: UserModel, listener: ApiListener<UserModel>) {
        val call = service.userLogin(userModel)

        call.enqueue(object : Callback<UserModel> {
            override fun onResponse(call: Call<UserModel>, response: Response<UserModel>) {
                if (response.isSuccessful) {
                    response.body()?.let { listener.onSuccess(it) }
                } else {
                    listener.onFailure(handleErrorResponseLogin(response))
                }
            }

            override fun onFailure(call: Call<UserModel>, t: Throwable) {
                listener.onFailure(t.message)
            }

        })
    }

    fun register(userModel: UserModel, listener: ApiListener<UserModel>) {
        val call = service.userRegister(userModel)

        call.enqueue(object : Callback<UserModel> {
            override fun onResponse(call: Call<UserModel>, response: Response<UserModel>) {
                if (response.isSuccessful) {
                    response.body()?.let { listener.onSuccess(it) }
                } else {
                    listener.onFailure(handleErrorResponseRegister(response))
                }
            }

            override fun onFailure(call: Call<UserModel>, t: Throwable) {
                listener.onFailure(t.message)
            }

        })
    }

    private fun handleErrorResponseLogin(response: Response<UserModel>): String {
        return try {
            val errorResponse =
                Gson().fromJson(response.errorBody()?.charStream(), ErrorResponseLogin::class.java)
            errorResponse?.message ?: "Unknown error occurred."
        } catch (e: Exception) {
            "Failed to parse error response."
        }
    }

    private fun handleErrorResponseRegister(response: Response<UserModel>): String {
        return try {
            val errorResponse = Gson().fromJson(response.errorBody()?.charStream(), ErrorResponseRegister::class.java)
            errorResponse?.message?.joinToString(",\n") ?: "Unknown error occurred."
        } catch (e: Exception) {
            "Failed to parse error response."
        }
    }
}