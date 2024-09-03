package com.idax.pokefight.di

import com.idax.pokefight.data.remote.PokemonService
import com.idax.pokefight.data.repository.PokemonRepository
import com.idax.pokefight.data.repository.PokemonRepositoryImp
import com.idax.pokefight.ui.HomeViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    //single(named("pokemonApiKey")) { BuildConfig.POKEMON_API_KEY }
}
val viewModelsModule = module {
    //viewModel { HomeViewModel() }
    viewModelOf(::HomeViewModel)
}

val dataModule = module {
    single {
        HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                })
            }
            install(DefaultRequest) {
                url {
                    protocol = URLProtocol.HTTPS
                    host = "pokeapi.co"
                }
            }
        }
    }
    single { PokemonRepositoryImp(get()) } bind PokemonRepository::class
    //factoryOf(::PokemonRepositoryImp)
    factoryOf(::PokemonService)
}

expect val nativeModule: Module

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(
            appModule, dataModule, viewModelsModule, nativeModule
        )
    }
}