package com.example.appvuelos_capebe.ui.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appvuelos_capebe.ui.components.Footer
import com.example.appvuelos_capebe.ui.components.Header

@Composable
fun Principal(
    onNavigateToPlanesVuelo: () -> Unit = {},
    onNavigateToRecursos: () -> Unit = {},
    onLogout: () -> Unit = {}
) {
    val context = LocalContext.current

    Scaffold(
        topBar = { Header("MENU PRINCIPAL CAPEBE") },
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
            Text(
                text = "Panel de Control",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(12.dp))

            // tarjeta con info del perfil
            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Rol: Operador de Pista",
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Base: Tobalaba / Talcahuano",
                        fontSize = 14.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // boton 1 planes de vuelo
            Button(
                onClick = { onNavigateToPlanesVuelo() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(text = "Planes de Vuelo")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // boton 2 recursos nativos
            Button(
                onClick = { onNavigateToRecursos() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(text = "Recursos Nativos")
            }

            Spacer(modifier = Modifier.height(32.dp))

            // boton 3 cerrar sesion
            OutlinedButton(
                onClick = {
                    Toast.makeText(context, "Sesion cerrada", Toast.LENGTH_SHORT).show()
                    onLogout()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Cerrar Sesion")
            }
        }
    }
}


