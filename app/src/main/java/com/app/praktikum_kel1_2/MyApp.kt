package com.app.praktikum_kel1_2

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.app.praktikum_kel1_2.components.BottomBar
import com.app.praktikum_kel1_2.navigation.Screen
import com.app.praktikum_kel1_2.navigation.SetupNavGraph

/**
 * Fungsi utama untuk menjalankan struktur UI aplikasi.
 * Menampilkan navigasi dan bottom bar sesuai dengan halaman yang sedang aktif.
 *
 * @param navController controller navigasi yang mengelola perpindahan antar layar.
 */
@Composable
fun MyApp(navController: NavHostController) {
    // Mengambil entry (halaman) aktif saat ini dari backstack
    val currentBackStack by navController.currentBackStackEntryAsState()

    // Mengambil route (nama halaman) dari entry aktif
    val currentRoute = currentBackStack?.destination?.route

    // Menentukan apakah bottom bar perlu ditampilkan berdasarkan halaman yang sedang aktif
    val showBottomBar = currentRoute in listOf(Screen.Home.route, Screen.Profile.route)

    // Box sebagai wadah utama layout
    Box(modifier = Modifier.fillMaxSize()) {

        // Scaffold menyusun struktur layout utama aplikasi (topBar, bottomBar, dan konten)
        Scaffold(
            containerColor = Color.Transparent, // Latar belakang transparan

            // Bottom bar hanya muncul di halaman Home dan Profile
            bottomBar = {
                if (showBottomBar) {
                    BottomBar(
                        navController = navController,
                        modifier = Modifier
                            .windowInsetsPadding(WindowInsets.navigationBars) // Beri padding agar tidak bentrok dengan sistem navigasi Android
                    )
                }
            }
        ) { contentPadding ->
            // Menampilkan isi halaman (konten utama) berdasarkan konfigurasi navigasi
            SetupNavGraph(
                navController = navController,
                modifier = Modifier.padding(contentPadding) // Menyesuaikan padding dari Scaffold
            )
        }
    }
}
