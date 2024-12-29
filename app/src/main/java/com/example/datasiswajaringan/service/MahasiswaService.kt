package com.example.datasiswajaringan.service

import com.example.datasiswajaringan.model.Mahasiswa
import okhttp3.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface MahasiswaService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )

    @GET("bacamahasiswa.php")
    suspend fun getAllMahasiswa(): List<Mahasiswa>

    @GET("baca1mahasiswa.php/{nim}")
    suspend fun getMahasiswabyNim (@Query("nim")nim: String): Mahasiswa

    @POST("insertmahasiswa.php")
    suspend fun insertMahasiswa(@Body mahasiswa: Mahasiswa)

    @PUT("editmahasiswa.php/{nim}")
    suspend fun updateMahasiswa(@Query("nim")nim: String):retrofit2.Response<Void>

    @DELETE("mahasiswa/{nim}")
    suspend fun deleteMahasiswa(@Path("nim") nim: String): retrofit2.Response<Unit>
}