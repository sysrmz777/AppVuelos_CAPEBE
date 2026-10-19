package com.example.appvuelos_capebe.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.appvuelos_capebe.data.local.dao.UsuarioDao
import com.example.appvuelos_capebe.data.local.entity.PlanVueloEntity
import com.example.appvuelos_capebe.data.local.entity.UsuarioEntity

@Database(
    entities = [UsuarioEntity::class, PlanVueloEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun usuarioDao(): UsuarioDao

    companion object { // propio del patron singleton
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "capebe_database.db" // nombre del archivo sqlite
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}