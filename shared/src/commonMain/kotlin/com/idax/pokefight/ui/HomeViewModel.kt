@file:OptIn(KoinExperimentalAPI::class)

package com.idax.pokefight.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.idax.pokefight.data.model.Pokemon
import com.idax.pokefight.data.repository.PokemonRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class HomeViewModel: ViewModel(), KoinComponent {

    private val pokemonRepository: PokemonRepository by inject()

    private var _state = MutableStateFlow(UIState())
    val state: StateFlow<UIState> get() = _state

    fun getPokemon() = viewModelScope.launch {
        setIsLoading(true)
        try {
            val response = pokemonRepository.getAll()
            println("response: $response")
            setPokemonList(response?.pokemonList)
        } finally {
            setIsLoading(false)
        }
    }

    private fun setIsLoading(isLoading: Boolean) {
        _state.update { it.updateIsLoading(isLoading) }
    }

    private fun setPokemonList(pokemonList: List<Pokemon>?) {
        _state.update { it.updatePokemonList(pokemonList.orEmpty()) }
    }

    data class UIState (
        val isLoading: Boolean = false,
        val pokemonList: List<Pokemon> = emptyList()
    ) {
        fun updateIsLoading(isLoading: Boolean) = copy(isLoading = isLoading)
        fun updatePokemonList(pokemonList: List<Pokemon>) = copy(pokemonList = pokemonList)
    }
}