package com.example.purelearn.api

data class SignUpRequest(
    val email: String,
    val password: String,
    val name:String
)