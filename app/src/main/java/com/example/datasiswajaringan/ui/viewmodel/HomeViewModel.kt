package com.example.datasiswajaringan.ui.viewmodel

import retrofit2.HttpException
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.datasiswajaringan.model.Mahasiswa
import com.example.datasiswajaringan.repository.MahasiswaRepository
import kotlinx.coroutines.launch
import java.io.IOException

sealed class HomeUiState {
    data class Success(val mahasiswa: List<Mahasiswa>) : HomeUiState()
    object Error : HomeUiState()
    object Loading : HomeUiState()
}

class HomeViewModel(private val mhs: MahasiswaRepository) : ViewModel() {

    var mhsUIState: HomeUiState by mutableStateOf(HomeUiState.Loading)
        private set

    fun getMhs() {
        viewModelScope.launch {
            mhsUIState = HomeUiState.Loading

            try {
                mhsUIState = HomeUiState.Success(mhs.getMahasiswa().data)
            } catch (e:IOException) {
                mhsUIState = HomeUiState.Error
            } catch (e:HttpException) {
                mhsUIState = HomeUiState.Error
            }
        }
    }

    fun deleteMhs(nim:String){
        viewModelScope.launch{
            try {
                mhs.deleteMahasiswa(nim)
            }catch (e:IOException){
                HomeUiState.Error
            }catch (e:HttpException){
                HomeUiState.Error
            }
        }
    }
}

