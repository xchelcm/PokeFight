package com.idax.pokefight.data.remote

import com.idax.pokefight.data.model.PokemonResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class PokemonService(
    private val client: HttpClient,
) {

    suspend fun getPokemonList(): PokemonResponse =
        client.get("/api/v2/pokemon").body()
}