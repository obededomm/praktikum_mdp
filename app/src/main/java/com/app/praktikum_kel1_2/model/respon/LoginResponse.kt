package com.app.praktikum_kel1_2.model.respon

data class LoginResponse(
    val code: Int,
    val message: String,
    val data: LoginData?,
    val token: String?
)

data class LoginData(
    val uuid: String,
    val fullName: String
)