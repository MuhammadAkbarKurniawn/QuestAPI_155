package com.example.datasiswajaringan.repository

import com.example.datasiswajaringan.model.ALlMahasiswaResponse
import com.example.datasiswajaringan.model.Mahasiswa
import com.example.datasiswajaringan.model.MahasiswaDetailResponse
import com.example.datasiswajaringan.service.MahasiswaService
import java.io.IOException

interface MahasiswaRepository{

    suspend fun getMahasiswa(): ALlMahasiswaResponse

    suspend fun insertMahasiswa(mahasiswa: Mahasiswa)

    suspend fun updateMahasiswa(nim: String, mahasiswa: Mahasiswa)

    suspend fun deleteMahasiswa(nim: String)

    suspend fun getMahasiswaById(nim: String): Mahasiswa
}

class NetworkMahasiswaRepository(
    private val MahasiswaApiService: MahasiswaService
) : MahasiswaRepository {


    override suspend fun getMahasiswa(): ALlMahasiswaResponse =
        MahasiswaApiService.getAllMahasiswa()

    override suspend fun insertMahasiswa(mahasiswa: Mahasiswa) {
        MahasiswaApiService.insertMahasiswa(mahasiswa)
    }

    override suspend fun updateMahasiswa(nim: String, mahasiswa: Mahasiswa) {
        MahasiswaApiService.updateMahasiswa(nim, mahasiswa)
    }

    override suspend fun deleteMahasiswa(nim: String) {
        try {
            val response = MahasiswaApiService.deleteMahasiswa(nim)
            if (!response.isSuccessful   ) {
                throw IOException("Failed to delete kontak. HTTP Status code: " +
                        "${response.code()}")
            } else {
                response.message()
                println(response.message())
            }
        } catch (e: Exception){
            throw  e
        }
    }

    override suspend fun getMahasiswaById(nim: String): Mahasiswa {
        return try {
            MahasiswaApiService.getMahasiswabyNim(nim).data
        } catch (e: Exception) {
            println("Error fetching mahasiswa by ID: ${e.message}")
            throw e
        }
    }
}
