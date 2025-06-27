package com.app.praktikum_kel1_2.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.app.praktikum_kel1_2.navigation.Screen

// Halaman hasil yang menerima input nama dari HomeScreen
@Composable
fun ResultScreen(name: String, navController: NavController) {
    // Box untuk menempatkan isi di tengah layar
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // Kolom berisi salam dan tombol kembali
        Column(modifier = Modifier.padding(16.dp)) {
            // Teks sapaan dengan gaya headline
            Text(
                text = "Halo, $name!",
                style = MaterialTheme.typography.headlineMedium
            )

            // Tombol untuk kembali ke halaman Home
            Button(onClick = {
                navController.navigate(route = Screen.Home.route)
            }) {
                Text("Kembali")
            }
        }
    }
}

// Preview halaman ResultScreen di Android Studio
@Composable
@Preview(showBackground = true)
fun ResultPreview() {
    ResultScreen(
        name = "",
        navController = rememberNavController() // Controller dummy untuk preview
    )
}
