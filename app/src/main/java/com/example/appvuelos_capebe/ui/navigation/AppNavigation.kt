package com.example.appvuelos_capebe.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.appvuelos_capebe.ui.screen.Login
import com.example.appvuelos_capebe.ui.screen.PantallaLogin
import com.example.appvuelos_capebe.ui.screen.PlanesVuelo
import com.example.appvuelos_capebe.ui.screen.Principal
import com.example.appvuelos_capebe.ui.screen.RecursosNativos
import com.example.appvuelos_capebe.ui.screen.Register

object Routes {
    const val INICIO = "inicio"
    const val LOGIN = "login"
    const val REGISTER = "register"
    const val PRINCIPAL = "principal"
    const val PLANES_VUELO = "planes_vuelo"
    const val RECURSOS = "recursos"
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.INICIO // definimos el inicio
    ) {
        // pantalla inicio
        composable(Routes.INICIO) {
            PantallaLogin(
                onNavigateToLogin = {
                    navController.navigate(Routes.LOGIN)
                }
            )
        }

        // pantalla login
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
                    navController.navigate(Routes.PRINCIPAL) {
                        popUpTo(Routes.LOGIN) { inclusive = true }
                    }
                }
            )
        }

        // pantalla registro
        composable(Routes.REGISTER) {
            Register(
                onNavigateToLogin = {
                    navController.navigate(Routes.LOGIN) {
                        popUpTo(Routes.LOGIN) { inclusive = true }
                    }
                }
            )
        }

        // pantalla principal
        composable(Routes.PRINCIPAL) {
            Principal(
                onNavigateToPlanesVuelo = {
                    navController.navigate(Routes.PLANES_VUELO)
                },
                onNavigateToRecursos = {
                    navController.navigate(Routes.RECURSOS)
                },
                onLogout = {
                    navController.navigate(Routes.LOGIN) {
                        popUpTo(Routes.PRINCIPAL) { inclusive = true }
                    }
                }
            )
        }

        // pantalla planes de vuelo
        composable(Routes.PLANES_VUELO) {
            PlanesVuelo(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }

        // pantalla recursos nativos
        composable(Routes.RECURSOS) {
            RecursosNativos(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}
