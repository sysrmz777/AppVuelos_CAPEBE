package com.example.appvuelos_capebe.model

data class PlanVueloModel(
    val matricula: String = "",
    val piloto: String = "",
    val pasajero: String = "",
    val pesoKg: String = "",
    val horasVuelo: String = "",
    val errorMessage: String? = null
)