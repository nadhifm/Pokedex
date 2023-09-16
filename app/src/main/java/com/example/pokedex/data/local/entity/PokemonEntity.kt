package com.example.pokedex.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PokemonEntity(
    val name: String,
    @PrimaryKey
    val url: String,
)
