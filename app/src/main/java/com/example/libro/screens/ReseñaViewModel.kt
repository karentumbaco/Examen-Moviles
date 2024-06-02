package com.example.libro.screens

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.libro.database.reseña.ReseñaEntidad
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.example.libro.Arranque
import com.example.libro.database.libro.LibroEntidad

class ReseñaViewModel : ViewModel() {

    private val _reseñas = mutableStateListOf<ReseñaEntidad>()
    val reseñas: SnapshotStateList<ReseñaEntidad> get() = _reseñas

    init {
        insertDefaultReseñasIfNeeded()
    }

    private fun insertDefaultReseñasIfNeeded() {
        viewModelScope.launch {
            val isDatabaseEmpty = withContext(Dispatchers.IO) {
                Arranque.room.getReseñaDao().getAllReseñas().isEmpty()
            }
            if (isDatabaseEmpty) {
                val defaultReseñas = listOf(
                    ReseñaEntidad(1, "Jacqueline", "Libro muy bueno, me gusto mucho el protagonista!.", 5, 1,"https://acortar.link/gP6Nr9"),
                    ReseñaEntidad(2, "Dario", "No estuvo malo el  libro, pero por varios momentos me aburrio.", 3, 1,"https://acortar.link/gP6Nr9"),
                    ReseñaEntidad(3, "Jacqueline", "Muy malo, me dormí leyendo esta basofia.", 0, 2,"https://acortar.link/GhCQn9")
                )
                withContext(Dispatchers.IO) {
                    Arranque.room.getReseñaDao().insert(defaultReseñas)
                }
                _reseñas.addAll(defaultReseñas)
            }
        }
    }

    fun fetchReseña(libroIdInt: Int) {
        viewModelScope.launch {
            val loadedReseñas = withContext(Dispatchers.IO) {
                Arranque.room.getReseñaDao().getReseñasById(libroIdInt)
            }
            println(loadedReseñas)
            _reseñas.clear()
            _reseñas.addAll(loadedReseñas)
            val snapshotStateList: SnapshotStateList<ReseñaEntidad> = _reseñas

            for (element in snapshotStateList) {
                println(element)
            }
        }
    }
}