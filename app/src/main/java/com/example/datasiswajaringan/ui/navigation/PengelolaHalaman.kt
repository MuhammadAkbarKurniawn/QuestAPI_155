package com.example.datasiswajaringan.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.datasiswajaringan.ui.view.DestinasiEntry
import com.example.datasiswajaringan.ui.view.DestinasiHome
import com.example.datasiswajaringan.ui.view.DetailScreen
import com.example.datasiswajaringan.ui.view.EntryMhsScreen
import com.example.datasiswajaringan.ui.view.HomeScreen
import com.example.datasiswajaringan.ui.view.UpdateScreen

@Composable
fun PengelolaHalaman(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route,
        modifier = Modifier
    ) {
        composable(DestinasiHome.route) {
            HomeScreen(
                navigateToItemEntry = {navController.navigate(DestinasiEntry.route)},
                onDetailClick = {nim ->
                    navController.navigate("detail/$nim")
                }
            )
        }
        composable(DestinasiEntry.route) {
            EntryMhsScreen(navigateBack = {
                navController.navigate(DestinasiHome.route) {
                    popUpTo(DestinasiHome.route){
                    inclusive = true
                    }
                }
            })
        }
        composable("detail/{nim}") { backStackEntry ->
            val nim = backStackEntry.arguments?.getString("nim") ?: ""
            DetailScreen(
                nim = nim,
                navigateBack = { navController.popBackStack() },
                onUpdateClick = { nim ->
                    navController.navigate("update/$nim")
                }
            )
        }
        composable("update/{nim}") { backStackEntry ->
            val nim = backStackEntry.arguments?.getString("nim") ?: ""
            UpdateScreen(
                nim = nim,
                navigateBack = { navController.popBackStack() }
            )
        }
    }
}