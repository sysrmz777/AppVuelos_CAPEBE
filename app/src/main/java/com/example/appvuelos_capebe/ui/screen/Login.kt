package com.example.appvuelos_capebe.ui.screen

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.appvuelos_capebe.ui.components.Footer
import com.example.appvuelos_capebe.ui.components.Header
import com.example.appvuelos_capebe.ui.viewModel.LoginViewModel

@Composable
fun Login(
    onNavigateToRegister: () -> Unit,
    onNavigateBackToHome: () -> Unit, // Para volver a la portada (Cerrar sesión)
    onLoginSuccess: () -> Unit = {},
    viewModel: LoginViewModel = viewModel()
) {
    val state = viewModel.uiState
    val context = LocalContext.current

    Scaffold(
        topBar = { Header("INICIAR SESIÓN") },
        bottomBar = { Footer() }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("L O G I N", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(24.dp))

            // Campo de Correo
            OutlinedTextField(
                value = state.email,
                onValueChange = { viewModel.onEmailChanged(it) },
                label = { Text("Correo Electrónico") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Campo de Contraseña
            OutlinedTextField(
                value = state.password,
                onValueChange = { viewModel.onPasswordChanged(it) },
                label = { Text("Contraseña") },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            // 1. "¿Olvidaste tu contraseña?" (Texto clickeable)
            Text(
                text = "¿Olvidaste tu contraseña?",
                color = MaterialTheme.colorScheme.primary,
                fontSize = 14.sp,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier
                    .align(Alignment.End)
                    .clickable {
                        Toast.makeText(context, "Se envió un enlace a tu correo", Toast.LENGTH_SHORT).show()
                    }
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Botón 1: Ingresar
            Button(
                onClick = {
                    if (state.email.isBlank() || state.password.isBlank()) {
                        Toast.makeText(context, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "¡Sesión iniciada con éxito!", Toast.LENGTH_SHORT).show()
                        onLoginSuccess()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Ingresar", fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Botón 2: Ir a Registro
            OutlinedButton(
                onClick = { onNavigateToRegister() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "¿No tienes cuenta? Regístrate aquí")
            }

            Spacer(modifier = Modifier.height(8.dp))

            // 2. "Volver / Salir" (Cerrar sesión)
            OutlinedButton(
                onClick = { onNavigateBackToHome() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Volver a Inicio")
            }
        }
    }
}