package com.example.pokedex.presentation.detail

import com.example.pokedex.domain.model.PokemonDetail
import com.example.pokedex.domain.model.Sprites

data class DetailState(
    val pokemonDetail: PokemonDetail = PokemonDetail(
        "",
        Sprites(""),
        listOf(),
    ),
    val url: String = "",
    val isLoading: Boolean = false,
)
