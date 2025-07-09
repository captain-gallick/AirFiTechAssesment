package com.nirvana.airfitechassesment.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nirvana.airfitechassesment.data.models.Airline
import com.nirvana.airfitechassesment.data.network.NetworkResult
import com.nirvana.airfitechassesment.data.repositories.AirlineRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AirlineViewModel @Inject constructor(
    private val repository: AirlineRepository
) : ViewModel() {

    private val _airlines = MutableStateFlow<NetworkResult<List<Airline>>>(NetworkResult.Loading)
    val airlines: StateFlow<NetworkResult<List<Airline>>> = _airlines.asStateFlow()

    init {
        fetchAirlines()
    }

    private fun fetchAirlines() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAirlines()
                .collect { result ->
                    if (result is NetworkResult.Success) {
                        // Save the fetched airlines to local storage
                        saveAirlines(result.data)
                    }
                    Log.d("RealmCheck", "fetchAirlines: $result")
                    _airlines.value = result
                }
        }
    }

    suspend fun getAirlineById(id: String): Airline? {
        return repository.getAirlineById(id)
    }

    private fun saveAirlines(data: List<Airline>) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.saveAirlines(data)
            repository.logData()
        }
    }

}
