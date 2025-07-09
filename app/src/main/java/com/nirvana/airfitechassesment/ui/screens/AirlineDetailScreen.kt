package com.nirvana.airfitechassesment.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.nirvana.airfitechassesment.data.models.Airline
import com.nirvana.airfitechassesment.ui.components.AirlineDetailsTable
import com.nirvana.airfitechassesment.ui.components.rememberUniversalImageLoader
import com.nirvana.airfitechassesment.ui.viewmodels.AirlineViewModel

@Composable
fun AirlineDetailScreen(
    airlineId: String,
    viewModel: AirlineViewModel = hiltViewModel()
) {
    val airline by produceState<Airline?>(initialValue = null) {
        value = viewModel.getAirlineById(airlineId)
    }

    airline?.let {
        Column(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                model = it.logo_url,
                contentDescription = "${it.name} logo",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .padding(16.dp),
                contentScale = ContentScale.Fit
            )

            AirlineDetailsTable(airline = it)
        }
    } ?: Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}


@Composable
@Preview(showSystemUi = true)
fun AirlineDetailScreenPreview() {
    AirlineDetailScreen(
        airlineId = "1",
        viewModel = hiltViewModel()
    )
}
