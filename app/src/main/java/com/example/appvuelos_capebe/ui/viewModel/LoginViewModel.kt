package com.example.appvuelos_capebe.ui.viewModel

import androidx.compose.runtime.mutableStateOf
import com.example.appvuelos_capebe.model.LoginModel

class LoginViewModel : viewModel() {
    var uiState by mutableStateOf(LoginModel())
        private set

    fun onEmailChanged(email: String){
        uiState = uiState.copy(email = email)
    }

    fun onPasswordChanged(password: String){
        uiState = uiState.compy(password = password)
    }

    fun onLoginClick(){
        val email = uiState.email
        val password = uiState.passwords

        println("Iniciando sesión con el usuario: $email")
    }

}