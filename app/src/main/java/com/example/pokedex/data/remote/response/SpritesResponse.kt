package com.example.pokedex.data.remote.response

import com.squareup.moshi.Json

data class SpritesResponse(
    @field:Json(name = "front_default")
    val frontDefault: String
)
