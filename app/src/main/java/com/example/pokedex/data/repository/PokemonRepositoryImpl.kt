package com.example.pokedex.data.repository

import com.example.pokedex.data.local.PokemonDao
import com.example.pokedex.data.mapper.toEntity
import com.example.pokedex.data.mapper.toModel
import com.example.pokedex.data.remote.network.APIService
import com.example.pokedex.domain.model.Pokemon
import com.example.pokedex.domain.model.PokemonDetail
import com.example.pokedex.domain.repository.PokemonRepository
import com.example.pokedex.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val apiService: APIService,
    private val pokemonDao: PokemonDao,
) : PokemonRepository {
    override fun getPokemons(query: String): Flow<Resource<List<Pokemon>>> = flow {
        emit(Resource.Loading())
        try {
            var pokemonsEntity = pokemonDao.getPokemons("%%").first()

            if (pokemonsEntity.isEmpty()) {
                val response = apiService.getPokemons().results
                pokemonDao.insert(response.map { it.toEntity() })
            }

            pokemonsEntity = pokemonDao.getPokemons("%$query%").first()
            emit(Resource.Success(pokemonsEntity.map { it.toModel() }))
        } catch (e: Exception) {
            emit(Resource.Error("An unexpected error occurred"))
        }
    }

    override fun getDetailPokemon(url: String): Flow<Resource<PokemonDetail>> = flow {
        emit(Resource.Loading())
        try {
            val response = apiService.getPokemonDetail(url)
            emit(Resource.Success(response.toModel()))
        } catch (e: Exception) {
            emit(Resource.Error("An unexpected error occurred"))
        }
    }
}