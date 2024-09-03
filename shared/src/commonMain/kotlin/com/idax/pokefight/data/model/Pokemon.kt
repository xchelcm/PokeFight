package com.idax.pokefight.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Pokemon(
    val name: String,
    val url: String
)