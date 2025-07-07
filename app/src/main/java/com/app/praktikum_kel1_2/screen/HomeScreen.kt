package com.app.praktikum_kel1_2.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.app.praktikum_kel1_2.components.NoteCard
import com.app.praktikum_kel1_2.model.viewModel.NotesViewModel
import com.app.praktikum_kel1_2.navigation.Screen

/**
 * Composable `HomeScreen` menampilkan halaman utama berisi input nama
 * dan tombol untuk navigasi ke halaman hasil (`ResultScreen`).
 *
 * @param navController controller navigasi untuk berpindah ke halaman berikutnya.
 */
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: NotesViewModel = viewModel ()
) {
  val noteState by viewModel.notes.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 24.dp)
    ) {
        if(noteState.isEmpty()){
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(noteState){
                    note ->
                    NoteCard(note)
                }
            }
        }

    }
}

/**
 * Preview `HomeScreen` untuk ditampilkan di Android Studio Preview.
 * Menggunakan dummy NavController untuk kebutuhan preview.
 */
@Preview(showBackground = true)
@Composable
fun HomeScreenView() {
    HomeScreen(
        navController = rememberNavController()
    )
}
