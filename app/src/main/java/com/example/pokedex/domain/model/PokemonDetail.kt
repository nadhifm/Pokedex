package com.example.pokedex.domain.model

data class PokemonDetail(
    val name: String,
    val sprites: Sprites,
    val abilities: List<Ability>
)
