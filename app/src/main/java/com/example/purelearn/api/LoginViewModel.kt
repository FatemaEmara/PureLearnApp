package com.example.purelearn.api

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.HttpException

class LoginViewModel : ViewModel() {
    var loginState = mutableStateOf("")

    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                val response = ApiClient.instance.login(email, password)
                if (response.isSuccessful) {
                    loginState.value = "Logged in successfully!"
                } else {
                    loginState.value = "Wrong email or password."
                }
            } catch (e: HttpException) {
                loginState.value = "An error occurred: ${e.message}"
            } catch (e: Exception) {
                loginState.value = "An unexpected error occurred."
            }
        }
    }
}
