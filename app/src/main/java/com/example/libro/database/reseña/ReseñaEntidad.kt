package com.example.libro.database.reseña

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.libro.database.libro.LibroEntidad

@Entity(tableName = "reseñas",
    foreignKeys = [
        ForeignKey(entity = LibroEntidad::class, parentColumns = ["id"], childColumns = ["idLibro"])
    ]
)
data class ReseñaEntidad(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "autor") val Autor: String,
    @ColumnInfo(name = "descripcion") val Descripcion: String,
    @ColumnInfo(name = "valoracion") val Valoracion: Int,
    @ColumnInfo(name = "idLibro") val IdLibro: Int,
    @ColumnInfo(name = "portada") val Portada: String
)
