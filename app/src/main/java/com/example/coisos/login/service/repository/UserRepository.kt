package com.example.coisos.login.service.repository

import com.example.coisos.login.service.constants.UserConstants
import com.example.coisos.login.service.listener.ApiListener
import com.example.coisos.login.service.model.UserModel
import com.example.coisos.login.service.repository.remote.RetrofitClient
import com.example.coisos.login.service.repository.remote.UserService
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.Reader


class UserRepository {

    private val service = RetrofitClient.getService(UserService::class.java)

    fun login(userModel: UserModel, listener: ApiListener<UserModel>) {
        val call = service.userLogin(userModel)

        call.enqueue(object : Callback<UserModel> {
            override fun onResponse(call: Call<UserModel>, response: Response<UserModel>) {
                if (response.code() == UserConstants.HTTP.SUCCESS) {
                    response.body()?.let { listener.onSuccess(it) }
                } else {
                    listener.onFailure(failResponse(response.errorBody()!!.charStream()))
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
                if (response.code() == UserConstants.HTTP.SUCCESS) {
                    response.body()?.let { listener.onSuccess(it) }
                } else {
                    listener.onFailure(failResponse(response.errorBody()!!.charStream()))
                }
            }

            override fun onFailure(call: Call<UserModel>, t: Throwable) {
                listener.onFailure(t.message)
            }

        })
    }

    private fun failResponse(str: Reader): String {
        val jsonObj = JSONObject(str.readText())
        return jsonObj.getString("error")
    }
}