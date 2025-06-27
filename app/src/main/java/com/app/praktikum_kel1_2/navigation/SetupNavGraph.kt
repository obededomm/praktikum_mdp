package com.app.praktikum_kel1_2.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.app.praktikum_kel1_2.screen.HomeScreen
import com.app.praktikum_kel1_2.screen.LoginScreen
import com.app.praktikum_kel1_2.screen.ProfileScreen
import com.app.praktikum_kel1_2.screen.RegisterScreen
import com.app.praktikum_kel1_2.screen.ResultScreen

/**
 * Fungsi `SetupNavGraph` digunakan untuk mengatur semua rute navigasi aplikasi.
 * Fungsi ini menentukan halaman awal (startDestination) dan halaman-halaman yang bisa diakses melalui navigasi.
 *
 * @param navController controller navigasi yang digunakan untuk berpindah halaman.
 * @param modifier modifier opsional untuk penyesuaian tampilan.
 */
@Composable
fun SetupNavGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    // Membuat NavHost untuk mengatur semua rute
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route, // Halaman awal aplikasi
        modifier = modifier
    ) {

        // Halaman Home
        composable(
            route = Screen.Home.route
        ) {
            HomeScreen(navController)
        }

        // Halaman Result, membutuhkan parameter "text"
        composable(
            route = Screen.Result.route,
            arguments = listOf(navArgument("text") {
                type = NavType.StringType // Tipe parameter: String
            })
        ) {
            // Mengambil argumen "text" dari route dan mengirim ke ResultScreen
            ResultScreen(it.arguments?.getString("text").toString(), navController)
        }

        // Halaman Profile
        composable(
            route = Screen.Profile.route
        ) {
            ProfileScreen(navController)
        }

        // Halaman Login
        composable(route = Screen.Login.route) {
            LoginScreen(navController)
        }

        // Halaman Register
        composable(route = Screen.Register.route) {
            RegisterScreen(navController)
        }
    }
}
