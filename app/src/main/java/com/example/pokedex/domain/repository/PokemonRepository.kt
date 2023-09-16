package com.example.pokedex.domain.repository

import com.example.pokedex.domain.model.Pokemon
import com.example.pokedex.domain.model.PokemonDetail
import com.example.pokedex.utils.Resource
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    fun getPokemons(query: String): Flow<Resource<List<Pokemon>>>
    fun getDetailPokemon(url: String): Flow<Resource<PokemonDetail>>
}