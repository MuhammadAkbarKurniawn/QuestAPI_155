package com.example.datasiswajaringan.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.datasiswajaringan.model.Mahasiswa
import com.example.datasiswajaringan.ui.customwidget.CostumeTopAppBar
import com.example.datasiswajaringan.ui.viewmodel.DetailUiState
import com.example.datasiswajaringan.ui.viewmodel.DetailViewModel
import com.example.datasiswajaringan.ui.viewmodel.PenyediaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    nim: String,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val detailUiState = viewModel.detailUiState.collectAsState().value

    Scaffold(
        modifier = modifier,
        topBar = {
            CostumeTopAppBar(
                title = "Detail Mahasiswa",
                canNavigateBack = true,
                navigateUp = navigateBack
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            when (detailUiState) {
                is DetailUiState.Loading -> OnLoading(modifier = Modifier.fillMaxSize())
                is DetailUiState.Success -> DetailContent(
                    mahasiswa = detailUiState.mahasiswa,
                    modifier = Modifier.fillMaxSize()
                )
                is DetailUiState.Error -> OnError(retryAction = { viewModel.getDetailMahasiswa(nim) })
            }
        }
    }
}

@Composable
fun DetailContent(mahasiswa: Mahasiswa, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Nama: ${mahasiswa.nama}",
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = "NIM: ${mahasiswa.nim}",
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = "Kelas: ${mahasiswa.kelas}",
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = "Alamat: ${mahasiswa.alamat}",
            style = MaterialTheme.typography.titleMedium
        )
    }
}
