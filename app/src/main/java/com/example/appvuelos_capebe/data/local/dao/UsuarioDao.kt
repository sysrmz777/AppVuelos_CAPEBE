package com.example.appvuelos_capebe.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.appvuelos_capebe.data.local.entity.PlanVueloEntity
import com.example.appvuelos_capebe.data.local.entity.UsuarioEntity

@Dao
interface UsuarioDao {

    // registrar un nuevo usuario en la base de datos sqlite
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun registerUser(user: UsuarioEntity)

    // busca un usuario por correo
    @Query("SELECT * FROM users WHERE email = :email LIMIT 1")
    suspend fun getUserByEmail(email: String): UsuarioEntity?

    // guardar un plan de vuelo
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarPlanVuelo(plan: PlanVueloEntity)

    // obtener todos los planes de vuelo
    @Query("SELECT * FROM planes_vuelo ORDER BY id DESC")
    suspend fun obtenerPlanesVuelo(): List<PlanVueloEntity>
}