package com.app.praktikum_kel1_2.model.respon

data class RegisterResponse(
    val message: String,
    val user: RegisterUser
)

data class RegisterUser(
    val id: String,
    val email: String,
    val username: String,
)