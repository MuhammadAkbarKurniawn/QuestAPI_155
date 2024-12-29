package com.example.datasiswajaringan.repository

import com.example.datasiswajaringan.model.Mahasiswa
import com.example.datasiswajaringan.service.MahasiswaService

interface MahasiswaRepository{

    suspend fun getMahasiswa(): List<Mahasiswa>

    suspend fun insertMahasiswa(mahasiswa: Mahasiswa)

    suspend fun updateMahasiswa(nim: String, mahasiswa: Mahasiswa)

    suspend fun deleteMahasiswa(nim: String)

    suspend fun getMahasiswaById(nim: String): Mahasiswa
}

class NetworkMahasiswaRepository(
    private val MahasiswaApiService: MahasiswaService
) : MahasiswaRepository {

    override suspend fun getMahasiswa(): List<Mahasiswa> =
        MahasiswaApiService.getAllMahasiswa()

    override suspend fun insertMahasiswa(mahasiswa: Mahasiswa) {
        MahasiswaApiService.insertMahasiswa(mahasiswa)
    }

    override suspend fun updateMahasiswa(nim: String, mahasiswa: Mahasiswa) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteMahasiswa(nim: String) {
        try {
            val response = MahasiswaApiService.deleteMahasiswa(nim)
            if (!response.isSuccessful) {
                throw Exception("Failed to delete Mahasiswa with NIM: $nim. Error: ${response.errorBody()?.string()}")
            }
        } catch (e: Exception) {
            println("Error deleting Mahasiswa: ${e.message}")
            throw e
        }
    }

    override suspend fun getMahasiswaById(nim: String): Mahasiswa {
        TODO("Not yet implemented")
    }
}
