package com.app.praktikum_kel1_2.model.request

/**
 * Data class `RegisterRequest` digunakan untuk mengirimkan data registrasi pengguna
 * dari aplikasi ke server saat proses pendaftaran akun.
 *
 * @param nm_lengkap nama lengkap pengguna.
 * @param email alamat email pengguna.
 * @param username nama pengguna yang akan digunakan untuk login.
 * @param password kata sandi akun yang akan dibuat.
 */
data class RegisterRequest(
    val nm_lengkap: String,
    val email: String,
    val username: String,
    val password: String
)
