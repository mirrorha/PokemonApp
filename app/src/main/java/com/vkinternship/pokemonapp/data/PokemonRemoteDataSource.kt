package com.vkinternship.pokemonapp.data

import com.vkinternship.pokemonapp.data.model.PokemonDetails
import com.vkinternship.pokemonapp.data.model.PokemonItem
import com.vkinternship.pokemonapp.data.model.PokemonListItem
import javax.inject.Inject



class PokemonRemoteDataSource @Inject constructor(
    private val pokemonApi: PokemonApi,
) {

    suspend fun fetchPokemons() = pokemonApi
            .fetchPokemons(100, 0)
            .results
            .map { pokemon ->
                PokemonListItem(
                    id = pokemon.url.split('/').let { it[it.size - 2].toInt() },
                    name = pokemon.name
                )
            }

    suspend fun fetchPokemonImageUrl(id: Int) = pokemonApi
        .fetchPokemon(id)
        .sprites
        .front_default

    suspend fun fetchPokemon(id: Int) = pokemonApi
        .fetchPokemon(id)
        .let {
            PokemonDetails(
                name = it.name,
                defaultUrl = it.sprites.front_default
            )
        }
}