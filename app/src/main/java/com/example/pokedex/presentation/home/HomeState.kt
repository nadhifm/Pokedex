package com.example.pokedex.presentation.home

import com.example.pokedex.domain.model.Pokemon
import com.example.pokedex.domain.utils.NameOrder

data class HomeState(
    val pokemons: List<Pokemon> = listOf(),
    val query: String = "",
    val nameOrder: NameOrder = NameOrder.Ascending,
    val isLoading: Boolean = false,
    val message: String = "",
    val isOrderSectionVisible: Boolean = false,
)
