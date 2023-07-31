package com.example.coisos.login.service.model

import com.google.gson.annotations.SerializedName

data class ErrorResponseLogin(
    @SerializedName("statusCode") val statusCode: Int,
    @SerializedName("message") val message: String,
)

data class ErrorResponseRegister(
    @SerializedName("statusCode") val statusCode: Int,
    @SerializedName("message") val message: List<String>,
    @SerializedName("error")val error: String
)