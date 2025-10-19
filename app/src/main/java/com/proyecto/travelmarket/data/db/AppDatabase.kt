package com.proyecto.travelmarket.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.proyecto.travelmarket.data.dao.UserDao
import com.proyecto.travelmarket.model.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    // Singleton para asegurar una única instancia de la DB
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "travel_market_db" // Nombre de tu base de datos
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}