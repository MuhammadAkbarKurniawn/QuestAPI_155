package com.example.datasiswajaringan.dependenciesinjection

import com.example.datasiswajaringan.repository.MahasiswaRepository
import com.example.datasiswajaringan.repository.NetworkMahasiswaRepository
import com.example.datasiswajaringan.service.MahasiswaService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val kontakRepository: MahasiswaRepository
}

class MahasiswaContainer : AppContainer {

    //private val baseUrl = "http://10.0.2.2:80/umyTI/" // localhost diganti IP jika dijalankan di perangkat lain
    private val baseUrl = "http://10.0.2.2:3000/api/mahasiswa/"
    private val json = Json {
        ignoreUnknownKeys = true
    }
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val mahasiswaService: MahasiswaService by lazy {
        retrofit.create(MahasiswaService::class.java)
    }

    override val kontakRepository: MahasiswaRepository by lazy {
        NetworkMahasiswaRepository(mahasiswaService)
    }
}
