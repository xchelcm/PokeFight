package com.idax.pokefight.data.repository

import com.idax.pokefight.data.model.PokemonResponse
import com.idax.pokefight.data.remote.PokemonService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

interface PokemonRepository {
    suspend fun getAll(): PokemonResponse?
}

class PokemonRepositoryImp(
    private val service: PokemonService
): PokemonRepository {
    override suspend fun getAll() = withContext(Dispatchers.IO) {
        service.getPokemonList()
    }
}