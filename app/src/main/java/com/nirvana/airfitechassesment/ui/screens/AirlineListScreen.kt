package com.nirvana.airfitechassesment.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.nirvana.airfitechassesment.data.models.Airline
import com.nirvana.airfitechassesment.data.network.NetworkResult
import com.nirvana.airfitechassesment.ui.components.AirlineCard
import com.nirvana.airfitechassesment.ui.viewmodels.AirlineViewModel

@Composable
fun AirlineListScreen(
    viewModel: AirlineViewModel = hiltViewModel(),
    onAirlineClick: (String) -> Unit
) {
    val uiState by viewModel.airlines.collectAsState()

    when (uiState) {
        is NetworkResult.Loading -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        is NetworkResult.Error -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Failed to load airlines")
            }
        }
        is NetworkResult.Success -> {
            val airlines = (uiState as NetworkResult.Success<List<Airline>>).data
            LazyColumn {
                items(airlines) { airline ->
                    AirlineCard(airline = airline) {
                        onAirlineClick(airline.id)
                    }
                }
            }
        }
    }
}

@Composable
@Preview(showSystemUi = true)
fun AirlineListScreenPreview() {
    AirlineListScreen(
        onAirlineClick = {}
    )
}
