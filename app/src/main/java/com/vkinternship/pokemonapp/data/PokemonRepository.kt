package com.vkinternship.pokemonapp.data

import com.vkinternship.pokemonapp.data.model.PokemonDetails
import com.vkinternship.pokemonapp.data.model.PokemonListItem


interface PokemonRepository {
    suspend fun fetchPokemons(): List<PokemonListItem>
    suspend fun fetchPokemonImageUrl(id: Int): String
    suspend fun fetchPokemon(id: Int): PokemonDetails
}