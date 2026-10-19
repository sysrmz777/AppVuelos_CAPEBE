package com.example.appvuelos_capebe.ui.viewModel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appvuelos_capebe.data.database.AppDatabase
import com.example.appvuelos_capebe.data.local.entity.PlanVueloEntity
import com.example.appvuelos_capebe.model.PlanVueloModel
import kotlinx.coroutines.launch

class PlanVueloViewModel : ViewModel() {
    var uiState by mutableStateOf(PlanVueloModel())
        private set

    var listaPlanes by mutableStateOf<List<PlanVueloEntity>>(emptyList())
        private set

    fun onMatriculaChanged(valor: String) {
        uiState = uiState.copy(matricula = valor, errorMessage = null)
    }

    fun onPilotoChanged(valor: String) {
        uiState = uiState.copy(piloto = valor, errorMessage = null)
    }

    fun onPasajeroChanged(valor: String) {
        uiState = uiState.copy(pasajero = valor, errorMessage = null)
    }

    fun onPesoChanged(valor: String) {
        uiState = uiState.copy(pesoKg = valor, errorMessage = null)
    }

    fun onHorasChanged(valor: String) {
        uiState = uiState.copy(horasVuelo = valor, errorMessage = null)
    }

    fun cargarPlanes(context: Context) {
        viewModelScope.launch {
            val db = AppDatabase.getDatabase(context)
            listaPlanes = db.usuarioDao().obtenerPlanesVuelo()
        }
    }

    fun guardarPlan(context: Context, onSuccess: () -> Unit) {
        val state = uiState

        if (state.matricula.isBlank() || state.piloto.isBlank() || state.pasajero.isBlank() || state.pesoKg.isBlank() || state.horasVuelo.isBlank()) {
            uiState = uiState.copy(errorMessage = "Completa todos los datos del vuelo.")
            return
        }

        val pesoNumero = state.pesoKg.toDoubleOrNull()
        if (pesoNumero == null || pesoNumero <= 0 || pesoNumero > 200) {
            uiState = uiState.copy(errorMessage = "Ingresa un peso valido en kg (1 a 200).")
            return
        }

        viewModelScope.launch {
            val db = AppDatabase.getDatabase(context)
            val nuevoPlan = PlanVueloEntity(
                matricula = state.matricula,
                piloto = state.piloto,
                pasajero = state.pasajero,
                pesoKg = state.pesoKg,
                horasVuelo = state.horasVuelo
            )
            db.usuarioDao().insertarPlanVuelo(nuevoPlan)
            listaPlanes = db.usuarioDao().obtenerPlanesVuelo()
            // limpiamos el formulario
            uiState = PlanVueloModel()
            onSuccess()
        }
    }
}