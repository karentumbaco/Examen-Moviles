import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.libro.Arranque
import com.example.libro.database.libro.LibroEntidad
import com.example.libro.database.reseña.ReseñaEntidad
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import androidx.compose.runtime.rememberCoroutineScope
import com.example.libro.Arranque.Companion.room

class LibrosViewModel : ViewModel() {

    private val _libros = mutableStateListOf<LibroEntidad>()
    val libros: SnapshotStateList<LibroEntidad> get() = _libros

    init {
        fetchLibros()
        insertDefaultLibrosIfNeeded()
    }

    private fun insertDefaultLibrosIfNeeded() {
        viewModelScope.launch {
            val isDatabaseEmpty = withContext(Dispatchers.IO) {
                Arranque.room.getLibroDao().getAllLibros().isEmpty()
            }
            if (isDatabaseEmpty) {
                val defaultLibros = listOf(
                    LibroEntidad(1, "Harry Potter 2", "Continuación de las aventuras del mago prodigio de Hogwarts!.", 15,"https://acortar.link/gP6Nr9"),
                    LibroEntidad(2, "La divina comedia", "Libro clásico muy reconocido.", 5,"https://acortar.link/GhCQn9"),
                    LibroEntidad(3, "Viaje al centro de la tierra", "Libro de ciencia ficcion que inspiro una pelicula.", 8, "https://acortar.link/JIgZyo")
                )
                withContext(Dispatchers.IO) {
                    Arranque.room.getLibroDao().insert(defaultLibros)
                }
                _libros.addAll(defaultLibros)
            }
        }
    }

    private fun fetchLibros() {
        viewModelScope.launch {
            val loadedLibros = withContext(Dispatchers.IO) {
                Arranque.room.getLibroDao().getAllLibros()
            }
            _libros.clear()
            _libros.addAll(loadedLibros)
        }
    }
}
