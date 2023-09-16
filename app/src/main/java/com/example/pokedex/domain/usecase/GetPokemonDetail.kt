package com.example.pokedex.domain.usecase

import com.example.pokedex.domain.model.PokemonDetail
import com.example.pokedex.domain.repository.PokemonRepository
import com.example.pokedex.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPokemonDetail @Inject constructor(
    private val repository: PokemonRepository
) {
    operator fun invoke(url: String): Flow<Resource<PokemonDetail>> {
        return repository.getDetailPokemon(url)
    }
}