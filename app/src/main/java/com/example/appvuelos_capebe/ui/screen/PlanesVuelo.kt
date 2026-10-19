package com.example.appvuelos_capebe.ui.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.appvuelos_capebe.ui.components.Footer
import com.example.appvuelos_capebe.ui.components.Header
import com.example.appvuelos_capebe.ui.viewModel.PlanVueloViewModel

@Composable
fun PlanesVuelo(
    onNavigateBack: () -> Unit,
    viewModel: PlanVueloViewModel = viewModel()
) {
    val state = viewModel.uiState
    val context = LocalContext.current

    Scaffold(
        topBar = { Header("PLANES DE VUELO CAPEBE") },
        bottomBar = { Footer() }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = "Registrar Plan de Vuelo",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            // matricula aeronave
            OutlinedTextField(
                value = state.matricula,
                onValueChange = { viewModel.onMatriculaChanged(it) },
                label = { Text("Aeronave / Matricula (ej: CC-CAP)") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(6.dp))

            // piloto
            OutlinedTextField(
                value = state.piloto,
                onValueChange = { viewModel.onPilotoChanged(it) },
                label = { Text("Piloto al mando") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(6.dp))

            // pasajero invitado
            OutlinedTextField(
                value = state.pasajero,
                onValueChange = { viewModel.onPasajeroChanged(it) },
                label = { Text("Nombre Pasajero / Socio") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(6.dp))

            // peso verificado en kg
            OutlinedTextField(
                value = state.pesoKg,
                onValueChange = { viewModel.onPesoChanged(it) },
                label = { Text("Peso verificado (kg)") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(6.dp))

            // horas de vuelo / horometro
            OutlinedTextField(
                value = state.horasVuelo,
                onValueChange = { viewModel.onHorasChanged(it) },
                label = { Text("Horas de vuelo / Bloque horario") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            // mensaje de error
            if (state.errorMessage != null) {
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = state.errorMessage!!,
                    color = Color.Red,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // boton guardar
            Button(
                onClick = {
                    viewModel.guardarPlan(context) {
                        Toast.makeText(context, "Plan de vuelo guardado en SQLite", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Guardar Plan de Vuelo")
            }

            Spacer(modifier = Modifier.height(6.dp))

            // boton actualizar squile
            Button(
                onClick = { viewModel.cargarPlanes(context) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Actualizar Lista (${viewModel.listaPlanes.size})")
            }

            Spacer(modifier = Modifier.height(6.dp))

            OutlinedButton(
                onClick = { onNavigateBack() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Volver al Menu")
            }

            Spacer(modifier = Modifier.height(8.dp))

            // muestra el ultimo vuelo
            viewModel.listaPlanes.take(2).forEach { plan ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 2.dp)
                ) {
                    Column(modifier = Modifier.padding(8.dp)) {
                        Text(text = "Avion: ${plan.matricula} | Piloto: ${plan.piloto}", fontWeight = FontWeight.Bold)
                        Text(text = "Pasajero: ${plan.pasajero} (${plan.pesoKg} kg) | Horas: ${plan.horasVuelo}")
                    }
                }
            }
        }
    }
}