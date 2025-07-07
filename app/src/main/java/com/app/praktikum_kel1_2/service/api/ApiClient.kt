package com.app.praktikum_kel1_2.service.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Objek singleton `ApiClient` digunakan untuk menginisialisasi Retrofit dan menyediakan
 * implementasi dari interface `ApiService`.
 *
 * Retrofit akan menghubungkan aplikasi dengan API backend menggunakan base URL yang telah ditentukan.
 */
object ApiClient {

    // Alamat dasar (base URL) dari backend API
    private const val BASE_URL = "https://notes-app-rosy-phi.vercel.app/"

    /**
     * Objek `instance` memberikan akses ke implementasi `ApiService`.
     * Inisialisasi dilakukan secara lazy untuk efisiensi.
     */
    val instance: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL) // Mengatur base URL
            .addConverterFactory(GsonConverterFactory.create()) // Menggunakan converter Gson
            .build()
            .create(ApiService::class.java) // Membuat implementasi ApiService
    }
}
