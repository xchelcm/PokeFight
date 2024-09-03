@file:OptIn(KoinExperimentalAPI::class)

package com.idax.pokefight.android.ui

import android.widget.ProgressBar
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.idax.pokefight.ui.HomeViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

/**
 * Home screen has options to:
 *  * battle
 *      * create room
 *      * select team
 *          * your team or a random team
 *      * config battle
 *          * time
 *          * lvl
 *          * set stats
 *      * start battle
 *  * see all pokemon
 *      * details
 *  * see your team
 *      * see your team
 *          * modified team
 *  * see your profile
 *      * teams
 *          * see your teams (no modified)
 *      * wins vs losses vs not finished (graphics)
 *      * best pokemon
 *  * see your config
 *  * see your achievements // TODO
 *  * see your battles
 *      * details
 *          * team
 *          * opponent name
 *          * battle config
 *      * total wins
 *      * total losses
 *      * total draws
 *      * total battles not finished
 *  * see your notifications // TODO
 *
 */
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel<HomeViewModel>()
) {
    val state by viewModel.state.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.getPokemon()
    }

    Column {
        Text(text = "Pokemon")
        if (state.isLoading) {
            LinearProgressIndicator()
        } else {
            LazyColumn {
                items(state.pokemonList) { pokemon ->
                    Text(text = pokemon.name)
                }
            }
        }
    }
}