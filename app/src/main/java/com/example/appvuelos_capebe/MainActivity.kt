package com.example.appvuelos_capebe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.appvuelos_capebe.ui.screen.PantallaLogin // ¡Importante! Esto conecta con tu pantalla
import com.example.appvuelos_capebe.ui.theme.AppVuelos_CAPEBETheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppVuelos_CAPEBETheme {
                // Aquí reemplazamos todo el relleno por tu Portada
                PantallaLogin()
            }
        }
    }
}