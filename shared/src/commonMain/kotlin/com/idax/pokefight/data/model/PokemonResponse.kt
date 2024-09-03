package com.idax.pokefight.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    @SerialName("results") val pokemonList: List<Pokemon>
)