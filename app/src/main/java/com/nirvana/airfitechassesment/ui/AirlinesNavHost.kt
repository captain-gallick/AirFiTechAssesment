package com.nirvana.airfitechassesment.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.nirvana.airfitechassesment.ui.screens.AirlineDetailScreen
import com.nirvana.airfitechassesment.ui.screens.AirlineListScreen

@Composable
fun AirlinesNavHost(navController: NavHostController) {
    NavHost(navController, startDestination = Screen.AirlineList.route) {
        composable(Screen.AirlineList.route) {
            AirlineListScreen { airlineId ->
                navController.navigate(Screen.AirlineDetail.createRoute(airlineId))
            }
        }
        composable(
            route = Screen.AirlineDetail.route,
            arguments = listOf(navArgument("airlineId") { type = NavType.StringType })
        ) { backStackEntry ->
            val airlineId = backStackEntry.arguments?.getString("airlineId") ?: ""
            AirlineDetailScreen(airlineId)
        }
    }
}
