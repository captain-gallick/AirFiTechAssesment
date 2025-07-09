package com.nirvana.airfitechassesment.ui

sealed class Screen(val route: String) {
    object AirlineList : Screen("airline_list")
    object AirlineDetail : Screen("airline_detail/{airlineId}") {
        fun createRoute(airlineId: String) = "airline_detail/$airlineId"
    }
}
