package com.example.datasiswajaringan.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.datasiswajaringan.R
import com.example.datasiswajaringan.model.Mahasiswa
import com.example.datasiswajaringan.ui.customwidget.CostumeTopAppBar
import com.example.datasiswajaringan.ui.navigation.DestinasiNavigasi
import com.example.datasiswajaringan.ui.viewmodel.DetailUiState
import com.example.datasiswajaringan.ui.viewmodel.DetailViewModel
import com.example.datasiswajaringan.ui.viewmodel.PenyediaViewModel

object DestinasiDetail : DestinasiNavigasi {
    override val route = "detail"
    override val titleRes = "Detail Mahasiswa"
    const val NIM = "nim"
    val routeWithArg = "$route/{$NIM}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    nim: String,
    navigateBack: () -> Unit,
    onUpdateClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val detailUiState = viewModel.detailUiState.collectAsState().value

    Scaffold(
        modifier = modifier,
        topBar = {
            CostumeTopAppBar(
                title = DestinasiDetail.titleRes,
                canNavigateBack = true,
                navigateUp = navigateBack,
                onRefresh = {
                    viewModel.getDetailMahasiswa(nim)
                }
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            when (detailUiState) {
                is DetailUiState.Loading -> OnLoadingDetail(modifier = Modifier.fillMaxSize())
                is DetailUiState.Success -> DetailContent(
                    mahasiswa = detailUiState.mahasiswa,
                    onUpdateClick = onUpdateClick,
                    modifier = Modifier.fillMaxSize()
                )
                is DetailUiState.Error -> OnError(retryAction = { viewModel.getDetailMahasiswa(nim) })
            }
        }
    }
}

@Composable
fun DetailContent(
    mahasiswa: Mahasiswa,
    onUpdateClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
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
                    text = "Angkatan: ${mahasiswa.angkatan}",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "Jenis Kelamin: ${mahasiswa.jenisKelamin}",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "Alamat: ${mahasiswa.alamat}",
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
        Button(
            onClick = { onUpdateClick(mahasiswa.nim) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Update Data")
        }
    }
}

@Composable
fun OnLoadingDetail(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.loading),
        contentDescription = stringResource(R.string.loading)
    )
}

@Composable
fun OnError(retryAction: () -> Unit) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "An error occurred. Please try again.")
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = retryAction) {
                Text(text = "Retry")
            }
        }
    }
}
