package com.app.praktikum_kel1_2.model.response

/**
 * Data class `RegisterResponse` merepresentasikan response dari server
 * setelah proses registrasi pengguna selesai.
 *
 * @param message pesan status dari server (contoh: "Registrasi berhasil").
 * @param user data pengguna yang baru saja terdaftar.
 */
data class RegisterResponse(
    val message: String,
    val user: RegisterUser
)

/**
 * Data class `RegisterUser` menyimpan data pengguna yang baru saja diregistrasi.
 *
 * @param id ID unik pengguna.
 * @param email alamat email pengguna.
 * @param username nama pengguna yang digunakan untuk login.
 */
data class RegisterUser(
    val id: String,
    val email: String,
    val username: String
)
