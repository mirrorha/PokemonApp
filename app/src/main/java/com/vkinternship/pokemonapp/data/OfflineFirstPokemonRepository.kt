package com.vkinternship.pokemonapp.data

import com.vkinternship.pokemonapp.data.model.PokemonDetails
import com.vkinternship.pokemonapp.data.model.PokemonListItem
import javax.inject.Inject

class OfflineFirstPokemonRepository @Inject constructor(
    private val pokemonRemoteDataSource: PokemonRemoteDataSource
) : PokemonRepository {
    override suspend fun fetchPokemons(): List<PokemonListItem> =
        pokemonRemoteDataSource.fetchPokemons()

    override suspend fun fetchPokemonImageUrl(id: Int): String =
        pokemonRemoteDataSource.fetchPokemonImageUrl(id)

    override suspend fun fetchPokemon(id: Int): PokemonDetails =
        pokemonRemoteDataSource.fetchPokemon(id)

}