package com.example.pokedex.domain.utils

sealed class NameOrder {
    object Ascending: NameOrder()
    object Descending: NameOrder()
}