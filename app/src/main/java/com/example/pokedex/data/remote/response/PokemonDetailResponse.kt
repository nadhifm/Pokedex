package com.example.pokedex.data.remote.response

data class PokemonDetailResponse(
    val name: String,
    val sprites: SpritesResponse,
    val abilities: List<AbilitiesResponse>
)
