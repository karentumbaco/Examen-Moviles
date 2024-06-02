package com.example.libro.database.libro

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface LibroDao {

    @Query("SELECT * FROM libros" )
    suspend fun getAllLibros():List<LibroEntidad>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(libros: List<LibroEntidad>)

}