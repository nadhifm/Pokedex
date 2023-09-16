package com.example.pokedex.presentation.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.domain.usecase.GetPokemons
import com.example.pokedex.domain.utils.NameOrder
import com.example.pokedex.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPokemons: GetPokemons
) : ViewModel() {
    var state by mutableStateOf(HomeState())

    private var searchJob: Job? = null

    init {
        viewModelScope.launch {
            onGetPokemons()
        }
    }

    fun onEvent(event: HomeEvent) {
        when(event) {
            is HomeEvent.OnQueryChange -> {
                state = state.copy(
                    query = event.query
                )
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(500L)
                    onGetPokemons()
                }
            }
            is HomeEvent.Order -> {
                state = state.copy(
                    nameOrder = event.nameOrder
                )
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    onGetPokemons()
                }
            }

            HomeEvent.ToggleOrderSection -> {
                state = state.copy(
                    isOrderSectionVisible = !state.isOrderSectionVisible
                )
            }
        }
    }

    private suspend fun onGetPokemons(
        query: String = state.query,
        nameOrder: NameOrder = state.nameOrder,
    ) {
        getPokemons(query, nameOrder).collect {
            when(it) {
                is Resource.Error -> {
                    state = state.copy(
                        isLoading = false,
                        message = it.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    state = state.copy(
                        isLoading = true,
                    )
                }
                is Resource.Success -> {
                    state = state.copy(
                        isLoading = false,
                        pokemons = it.data ?: listOf()
                    )
                }
            }
        }
    }
}