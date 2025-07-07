package com.app.praktikum_kel1_2.model.response

/**
 * Data class `LoginResponse` merepresentasikan response dari server
 * setelah pengguna melakukan login.
 *
 * @param code kode status dari server (contoh: 200 untuk sukses).
 * @param message pesan status dari server (contoh: "Login berhasil").
 * @param data data pengguna yang berhasil login.
 * @param token token otentikasi yang diberikan server untuk sesi login.
 */
data class LoginResponse(
    val code: Int,
    val message: String,
    val data: LoginData?,
    val token: String?
)

/**
 * Data class `LoginData` menyimpan informasi pengguna setelah berhasil login.
 *
 * @param uuid ID unik pengguna.
 * @param fullName nama lengkap pengguna.
 */
data class LoginData(
    val uuid: String,
    val fullName: String
)
