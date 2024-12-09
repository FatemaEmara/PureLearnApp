package com.example.purelearn.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



object getInstance {
    //api/student/signup
 private const val baseUrl="http://gradproj.runasp.net/api/student/"

    private fun getInstance() : Retrofit
    {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val signUpApi:Api = getInstance().create(Api::class.java)
    //  val loginApi:ApiService = getInstance().create(ApiService::class.java)

}