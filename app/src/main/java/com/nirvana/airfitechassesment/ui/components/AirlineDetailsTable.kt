package com.nirvana.airfitechassesment.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.nirvana.airfitechassesment.data.models.Airline

@Composable
fun AirlineDetailsTable(airline: Airline) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        DetailRow(label = "Name", value = airline.name)
        DetailRow(label = "Country", value = airline.country)
        DetailRow(label = "Headquarters", value = airline.headquarters)
        DetailRow(label = "Fleet Size", value = airline.fleet_size.toString())
        DetailRow(label = "Website", value = airline.website, isLink = true)
    }
}

@Composable
fun DetailRow(label: String, value: String, isLink: Boolean = false) {
    val uriHandler = LocalUriHandler.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.weight(1f)
        )

        if (isLink) {

            Text(
                text = AnnotatedString(value),
                textAlign = TextAlign.End,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier.weight(1f)
                    .clickable(
                        onClick = { uriHandler.openUri(value) },
                    )
            )
        } else {
            Text(
                text = value,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.End
            )
        }
    }

    HorizontalDivider()
}
