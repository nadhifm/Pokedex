package com.example.pokedex.domain.usecase

import androidx.compose.ui.text.toLowerCase
import com.example.pokedex.domain.model.Pokemon
import com.example.pokedex.domain.repository.PokemonRepository
import com.example.pokedex.domain.utils.NameOrder
import com.example.pokedex.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetPokemons @Inject constructor(
    private val repository: PokemonRepository
) {
    operator fun invoke(
        query: String,
        nameOrder: NameOrder,
    ): Flow<Resource<List<Pokemon>>> {
        return repository.getPokemons(query).map { resource ->
            when(resource) {
                is Resource.Success -> {
                    when(nameOrder) {
                        is NameOrder.Ascending -> {
                            Resource.Success(resource.data?.sortedBy { it.name })
                        }
                        is NameOrder.Descending -> {
                            Resource.Success(resource.data?.sortedByDescending { it.name })
                        }
                    }
                }
                else -> {
                    resource
                }
            }
        }
    }
}