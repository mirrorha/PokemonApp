package com.vkinternship.pokemonapp.data

import com.vkinternship.pokemonapp.data.model.PokemonDetails
import com.vkinternship.pokemonapp.data.model.PokemonListItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
class PokemonRemoteDataSource @Inject constructor(
    private val pokemonApi: PokemonApi,
    private val ioDispatcher: CoroutineDispatcher
) {

    suspend fun fetchPokemons() =

        withContext(ioDispatcher) {
            pokemonApi
                .fetchPokemons(200, 0)
                .results
                .map { pokemon ->
                    PokemonListItem(
                        id = pokemon.url.split('/').let { it[it.size - 2].toInt() },
                        name = pokemon.name
                    )
                }
        }


    suspend fun fetchPokemonImageUrl(id: Int) =
        withContext(ioDispatcher) {
            pokemonApi
                .fetchPokemon(id)
                .sprites
                .front_default
        }

    suspend fun fetchPokemon(id: Int) =
        withContext(ioDispatcher) {
            pokemonApi
                .fetchPokemon(id)
                .let {
                    PokemonDetails(
                        name = it.name,
                        defaultUrl = it.sprites.front_default
                    )
                }
        }
}


