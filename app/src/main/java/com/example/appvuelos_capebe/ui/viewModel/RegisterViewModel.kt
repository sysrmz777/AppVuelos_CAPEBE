package com.example.appvuelos_capebe.ui.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.appvuelos_capebe.model.RegisterModel

class RegisterViewModel : ViewModel() {
    var uiState by mutableStateOf(RegisterModel())
        private set

    fun onNameChanged(name: String) {
        uiState = uiState.copy(name = name, errorMessage = null)
    }

    fun onEmailChanged(email: String) {
        uiState = uiState.copy(email = email, errorMessage = null)
    }

    fun onPasswordChanged(password: String) {
        uiState = uiState.copy(password = password, errorMessage = null)
    }

    fun onConfirmPasswordChanged(confirmPassword: String) {
        uiState = uiState.copy(confirmPassword = confirmPassword, errorMessage = null)
    }

    fun onRegisterClick(onSuccess: () -> Unit) {
        val state = uiState

        if (state.name.isBlank() || state.email.isBlank() || state.password.isBlank()) {
            uiState = uiState.copy(errorMessage = "Por favor completa todos los campos.")
            return
        }

        if (state.password != state.confirmPassword) {
            uiState = uiState.copy(errorMessage = "Las contraseñas no coinciden.")
            return
        }

        uiState = uiState.copy(errorMessage = null)
        onSuccess()
    }
}
