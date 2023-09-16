package com.example.pokedex.presentation.detail

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.domain.model.PokemonDetail
import com.example.pokedex.domain.model.Sprites
import com.example.pokedex.domain.usecase.GetPokemonDetail
import com.example.pokedex.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getPokemonDetail: GetPokemonDetail,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    var state by mutableStateOf(DetailState())

    init {
        savedStateHandle.get<String>("url")?.let { url ->
            if (url != "") {
                state = state.copy(
                    url = url
                )
                onGetPokemonDetail()
            }
        }
    }

    private fun onGetPokemonDetail(url: String = state.url) {
        viewModelScope.launch {
            getPokemonDetail(url).collect {
                when(it) {
                    is Resource.Error -> {
                        state = state.copy(
                            isLoading = false,
                        )
                    }
                    is Resource.Loading -> {
                        state = state.copy(
                            isLoading = true,
                        )
                    }
                    is Resource.Success -> {
                        Log.d("URLLLL", it.data.toString())
                        state = state.copy(
                            isLoading = false,
                            pokemonDetail = it.data ?: PokemonDetail(
                                "",
                                Sprites(""),
                                listOf(),
                            )
                        )
                    }
                }
            }
        }
    }
}