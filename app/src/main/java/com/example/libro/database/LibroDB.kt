package com.example.libro.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.libro.database.libro.LibroDao
import com.example.libro.database.libro.LibroEntidad
import com.example.libro.database.reseña.ReseñaDao
import com.example.libro.database.reseña.ReseñaEntidad

@Database(entities = [LibroEntidad::class, ReseñaEntidad::class], version = 4)
abstract class LibroDB: RoomDatabase(){
    abstract fun getLibroDao(): LibroDao
    abstract fun getReseñaDao(): ReseñaDao
}