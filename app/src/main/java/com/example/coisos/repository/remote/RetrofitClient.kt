package com.example.coisos.repository.remote
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    companion object {

        private lateinit var INSTANCE: Retrofit

        private fun getRetrofitInstance(): Retrofit {

            val httpClient = OkHttpClient.Builder()

            if (!::INSTANCE.isInitialized) {
                synchronized(RetrofitClient::class.java) {
                    INSTANCE = Retrofit.Builder()
                        .baseUrl("http://10.0.2.2:3000/")
                        .client(httpClient.build())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                }
            }

            return INSTANCE
        }

        fun <T> getService(serviceClass: Class<T>): T {
            return getRetrofitInstance().create(serviceClass)
        }
    }
}