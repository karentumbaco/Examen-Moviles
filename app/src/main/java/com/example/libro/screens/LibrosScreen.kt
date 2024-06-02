package com.example.libro.screens

import LibrosViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.ExperimentalMaterial3Api
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.libro.R
import com.example.libro.database.libro.LibroEntidad
import com.example.libro.navigation.AppScreens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest


@Composable
fun LibrosScreen(navController: NavController, librosViewModel: LibrosViewModel = viewModel()) {
    val libros by remember { derivedStateOf { librosViewModel.libros } }
    if (libros.isNotEmpty()) {
        Column {
            Card(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier
                        .background(color = Color.LightGray)
                        .fillMaxWidth()
                        .padding(horizontal = 80.dp)
                ) {
                    Text(text = "Registro de datos", fontWeight = FontWeight.SemiBold, fontStyle = FontStyle.Italic)
                }
            }
            libros.forEach { libro ->
                LibroData(navController, libro)
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    } else {
        Text(text = "No hay libros registrados")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LibroData(navController: NavController, libro: LibroEntidad) {
    Card(
        onClick = {
            println(libro.id)
            navController.navigate("${AppScreens.Reseña.route}/${libro.id}")
        },
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
                        .data(libro.Portada)
                        .build(),
                    contentDescription = "",
                    modifier = Modifier
                        .scale(1f)
                        .size(100.dp),
                )
                Column() {
                    Text(text = "ID: ${libro.id}")
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(text = "Nombre: ${libro.Nombre}")
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(text = "Precio: ${libro.Precio}")
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(text = "Descripción: ${libro.Descripcion}")

                }
            }

        }
    }
}