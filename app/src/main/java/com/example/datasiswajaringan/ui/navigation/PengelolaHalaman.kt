package com.example.datasiswajaringan.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.datasiswajaringan.ui.view.DestinasiDetail
import com.example.datasiswajaringan.ui.view.DestinasiEntry
import com.example.datasiswajaringan.ui.view.DestinasiHome
import com.example.datasiswajaringan.ui.view.DestinasiUpdate
import com.example.datasiswajaringan.ui.view.DetailScreen
import com.example.datasiswajaringan.ui.view.EntryMhsScreen
import com.example.datasiswajaringan.ui.view.HomeScreen
import com.example.datasiswajaringan.ui.view.UpdateScreen
@Composable
fun PengelolaHalaman(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route,
        modifier = modifier,
    ) {
        // Home Screen
        composable(DestinasiHome.route) {
            HomeScreen(
                navigateToItemEntry = { navController.navigate(DestinasiEntry.route) },
                onDetailClick = { nim ->
                    // Navigasi ke rute Detail dengan argumen nim
                    navController.navigate("${DestinasiDetail.route}/$nim")
                }
            )
        }

        // Entry Screen
        composable(DestinasiEntry.route) {
            EntryMhsScreen(
                navigateBack = {
                    navController.navigate(DestinasiHome.route) {
                        popUpTo(DestinasiHome.route) { inclusive = true }
                    }
                }
            )
        }

        // Detail Screen
        composable(
            route = "${DestinasiDetail.route}/{nim}",
            arguments = listOf(
                navArgument("nim") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val nim = backStackEntry.arguments?.getString("nim") // Ambil argumen nim
            nim?.let {
                DetailScreen(
                    nim = it,
                    navigateBack = { navController.popBackStack() },
                    onUpdateClick = { navController.navigate("${DestinasiUpdate.route}/$it") }
                )
            }
        }

        // Update Screen
        composable(
            route = "${DestinasiUpdate.route}/{nim}",
            arguments = listOf(
                navArgument("nim") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val nim = backStackEntry.arguments?.getString("nim") // Ambil argumen nim
            nim?.let {
                UpdateScreen(
                    nim = it,
                    navigateBack = { navController.popBackStack() }
                )
            }
        }
    }
}
