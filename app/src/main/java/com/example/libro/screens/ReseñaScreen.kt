package com.example.libro.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.libro.database.reseña.ReseñaEntidad
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun ReseñaScreen(navController: NavController, libroId: String?, reseñaViewModel: ReseñaViewModel = viewModel()) {
    val libroIdInt: Int = libroId?.toIntOrNull() ?: 0

    val reseña by remember { derivedStateOf { reseñaViewModel.reseñas } }

    LaunchedEffect(libroIdInt) {
        reseñaViewModel.fetchReseña(libroIdInt)
    }

    if (reseña.isNotEmpty()) {

        Column {
            Card(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier
                        .background(color = Color.LightGray)
                        .fillMaxWidth()
                        .padding(horizontal = 80.dp)
                ) {
                    Text(
                        text = "Reseñas",
                        fontWeight = FontWeight.SemiBold,
                        fontStyle = FontStyle.Italic
                    )
                }
            }
            ReseñaData2(reseña)
        }
    } else {
        Text(text = "No se encontró ninguna reseña.")
    }
}

@Composable
fun ReseñaData2(reseñas: List<ReseñaEntidad>) {
    reseñas.forEach { reseña ->
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .padding(20.dp)
                .zIndex(4f),
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(Modifier.padding(20.dp)) {
                Row() {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(reseña.Portada)
                            .build(),
                        contentDescription = "",
                        modifier = Modifier
                            .scale(1f)
                            .size(100.dp),
                    )
                    Column() {
                        Text(text = "ID: ${reseña.id}")
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(text = "Autor: ${reseña.Autor}")
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(text = "Descripción: ${reseña.Descripcion}")
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(text = "Valoracion: ${reseña.Valoracion}")
                        Spacer(modifier = Modifier.height(5.dp))

                    }

                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
    }
}

