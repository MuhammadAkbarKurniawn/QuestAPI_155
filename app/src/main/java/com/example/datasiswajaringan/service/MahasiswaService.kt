package com.example.datasiswajaringan.service

import com.example.datasiswajaringan.model.ALlMahasiswaResponse
import com.example.datasiswajaringan.model.Mahasiswa
import com.example.datasiswajaringan.model.MahasiswaDetailResponse
import retrofit2.Response
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
        "Content-Type: application/json"
    )

    //@POST("insertmahasiswa.php")
    @POST("store")
    suspend fun insertMahasiswa(@Body mahasiswa: Mahasiswa)

    //@GET("bacamahasiswa.php")
    @GET(".")
    suspend fun getAllMahasiswa(): ALlMahasiswaResponse

    //@GET("baca1mahasiswa.php")
    @GET("{nim}")
    //suspend fun getMahasiswabyNim(@Query("nim") nim: String): MahasiswaDetailResponse
    suspend fun getMahasiswabyNim(@Path("nim") nim: String): MahasiswaDetailResponse

    //@PUT("editmahasiswa.php")
    @PUT("{nim}")
    suspend fun updateMahasiswa(@Path("nim") nim: String, @Body mahasiswa: Mahasiswa)

    //@DELETE("deletemahasiswa.php")
    @DELETE("{nim}")
    suspend fun deleteMahasiswa(@Path("nim") nim: String): Response<Void>
}