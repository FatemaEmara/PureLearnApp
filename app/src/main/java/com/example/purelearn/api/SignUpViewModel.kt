package com.example.purelearn.api

import android.util.Log
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SignUpViewModel : ViewModel() {
    //val context = LocalContext.current
    private val _signUpState = MutableStateFlow<SignUpState>(SignUpState.Idle)
    val signUpState: StateFlow<SignUpState> = _signUpState

    fun signUp(email: String, password: String, name: String) {
        // Validate input
        if (email.isBlank() || !email.contains("@")) {
            _signUpState.value = SignUpState.Error("Invalid email format.")
            return
        }
        if (password.isBlank()) {
            _signUpState.value = SignUpState.Error("Password cannot be empty.")
            return
        }
        if (name.isBlank()) {
            _signUpState.value = SignUpState.Error("Name cannot be empty.")
            return
        }

        // Start the API call
        _signUpState.value = SignUpState.Loading
        viewModelScope.launch {
            try {
                val response = getInstance.signUpApi.signUp(email,password,name)
                if (response.isSuccessful) {
                    Log.i("Response : ",response.body().toString())
                    _signUpState.value = SignUpState.Success("Sign up successful!")
                } else {
                    Log.i("Error : ",response.message())
                    _signUpState.value = SignUpState.Error(response.errorBody()?.string() ?: "An error occurred.")
                }
            } catch (e: Exception) {
                _signUpState.value = SignUpState.Error("Network error: ${e.message}")
            }
        }
    }




}

sealed class SignUpState {
    object Idle : SignUpState()
    object Loading : SignUpState()
    data class Success(val message: String) : SignUpState()
    data class Error(val error: String) : SignUpState()
}
