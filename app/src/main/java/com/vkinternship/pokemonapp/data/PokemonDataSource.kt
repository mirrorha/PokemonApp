package com.vkinternship.pokemonapp.data

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class PokemonRemoteDataSource(
    private val pokemonApi: PokemonApi,
    private val ioDispatcher: CoroutineDispatcher
) {

    suspend fun fetchPokemons(): List<> =
        withContext(ioDispatcher) {
            pokemonApi.fetchPokemons()
        }

}