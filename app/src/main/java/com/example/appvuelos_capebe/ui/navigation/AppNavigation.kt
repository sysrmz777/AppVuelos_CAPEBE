package com.example.appvuelos_capebe.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.appvuelos_capebe.ui.screen.Login
import com.example.appvuelos_capebe.ui.screen.PantallaLogin
import com.example.appvuelos_capebe.ui.screen.Register

object Routes {
    const val INICIO = "inicio"
    const val LOGIN = "login"
    const val REGISTER = "register"
    const val PRINCIPAL = "principal"
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.INICIO
    ) {
        // RUTA 1 TU PORTADA
        composable(Routes.INICIO) {
            PantallaLogin(
                onNavigateToLogin = {
                    navController.navigate(Routes.LOGIN)
                }
            )
        }

        // RUTA 2 INICIAR SESION
        composable(Routes.LOGIN) {
            Login(
                onNavigateToRegister = {
                    navController.navigate(Routes.REGISTER)
                },
                onNavigateBackToHome = {
                    navController.navigate(Routes.INICIO) {
                        popUpTo(Routes.INICIO) { inclusive = true }
                    }
                },
                onLoginSuccess = {

                }
            )
        }

        // RUTA 3 REGISTRO DE USUARIOS
        composable(Routes.REGISTER) {
            Register(
                onNavigateToLogin = {
                    navController.navigate(Routes.LOGIN) {
                        // Limpia
                        popUpTo(Routes.LOGIN) { inclusive = true }
                    }
                }
            )
        }
    }
}