package com.example.pokedex.data.mapper

import com.example.pokedex.data.local.entity.PokemonEntity
import com.example.pokedex.data.remote.response.PokemonDetailResponse
import com.example.pokedex.data.remote.response.PokemonResponse
import com.example.pokedex.domain.model.Ability
import com.example.pokedex.domain.model.Pokemon
import com.example.pokedex.domain.model.PokemonDetail
import com.example.pokedex.domain.model.Sprites

fun PokemonResponse.toEntity(): PokemonEntity = PokemonEntity(
    name = name,
    url = url,
)

fun PokemonEntity.toModel(): Pokemon = Pokemon(
    name = name,
    url = url,
)

fun PokemonDetailResponse.toModel(): PokemonDetail = PokemonDetail(
    name = name,
    sprites = Sprites(sprites.frontDefault),
    abilities = abilities.map { Ability(it.ability.name) },
)