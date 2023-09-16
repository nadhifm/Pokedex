package com.example.pokedex.presentation.home

import com.example.pokedex.domain.utils.NameOrder

sealed class HomeEvent {
    data class OnQueryChange(val query: String) : HomeEvent()
    data class Order(val nameOrder: NameOrder) : HomeEvent()
    object ToggleOrderSection : HomeEvent()
}