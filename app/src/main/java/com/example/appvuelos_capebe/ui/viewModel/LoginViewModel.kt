package com.example.appvuelos_capebe.ui.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.appvuelos_capebe.model.LoginModel

class LoginViewModel : ViewModel() {
    var uiState by mutableStateOf(LoginModel())
        private set

    fun onEmailChanged(email: String) {
        uiState = uiState.copy(email = email)
    }

    fun onPasswordChanged(password: String) {
        uiState = uiState.copy(password = password)
    }

    fun onLoginClick() {
        val email = uiState.email
        val password = uiState.password

        println("Iniciando sesión con el usuario: $email")
    }
}