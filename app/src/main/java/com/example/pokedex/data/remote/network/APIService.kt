package com.example.pokedex.data.remote.network

import com.example.pokedex.data.remote.response.GetPokemonResponse
import com.example.pokedex.data.remote.response.PokemonDetailResponse
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {
    @GET("pokemon/")
    suspend fun getPokemons(): GetPokemonResponse

    @GET
    suspend fun getPokemonDetail(
        @Url url: String,
    ): PokemonDetailResponse
}