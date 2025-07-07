package com.app.praktikum_kel1_2.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

/**
 * Composable `ProfileScreen` digunakan untuk menampilkan halaman profil pengguna.
 * Saat ini hanya menampilkan teks statis sebagai placeholder.
 *
 * @param navController controller navigasi, disediakan untuk kebutuhan navigasi meskipun belum digunakan.
 */
@Composable
fun ProfileScreen(navController: NavController) {
    Text("Ini Profile Screen")
}

/**
 * Preview `ProfileScreen` untuk dilihat pada Android Studio Preview.
 * Menggunakan `rememberNavController` sebagai controller dummy.
 */
@Preview(showBackground = true)
@Composable
fun PrfileScreenView() {
    ProfileScreen(
        navController = rememberNavController()
    )
}
