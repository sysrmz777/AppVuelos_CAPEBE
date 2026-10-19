package com.example.appvuelos_capebe.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UsuarioEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val email: String,
    val password: String
)

@Entity(tableName = "planes_vuelo")
data class PlanVueloEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val matricula: String,
    val piloto: String,
    val pasajero: String,
    val pesoKg: String,
    val horasVuelo: String
)