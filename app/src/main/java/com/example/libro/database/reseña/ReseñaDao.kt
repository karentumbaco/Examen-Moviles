package com.example.libro.database.reseña

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.libro.database.libro.LibroEntidad

@Dao
interface ReseñaDao {

    @Query("SELECT * FROM reseñas" )
    suspend fun getAllReseñas():List<ReseñaEntidad>

    @Query("SELECT * FROM reseñas WHERE idLibro = :idLibro" )
    suspend fun getReseñasById(idLibro: Int): List<ReseñaEntidad>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(reseñas: List<ReseñaEntidad>)
}