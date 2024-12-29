package com.example.datasiswajaringan.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.datasiswajaringan.model.Mahasiswa
import com.example.datasiswajaringan.repository.MahasiswaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class DetailUiState {
    data class Success(val mahasiswa: Mahasiswa) : DetailUiState()
    object Error : DetailUiState()
    object Loading : DetailUiState()
}

class DetailViewModel(private val mhsRepository: MahasiswaRepository) : ViewModel() {

    private val _detailUiState = MutableStateFlow<DetailUiState>(DetailUiState.Loading)
    val detailUiState: StateFlow<DetailUiState> = _detailUiState.asStateFlow()

    fun getDetailMahasiswa(nim: String) {
        viewModelScope.launch {
            _detailUiState.value = DetailUiState.Loading
            try {
                val mahasiswa = mhsRepository.getMahasiswaById(nim)
                _detailUiState.value = DetailUiState.Success(mahasiswa)
            } catch (e: Exception) {
                _detailUiState.value = DetailUiState.Error
            }
        }
    }
}