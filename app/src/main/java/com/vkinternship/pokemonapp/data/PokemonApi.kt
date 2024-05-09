package com.vkinternship.pokemonapp.data

import com.vkinternship.pokemonapp.data.model.PokemonListResponse
import com.vkinternship.pokemonapp.data.model.PokemonResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApi {
    @GET("pokemon")
    suspend fun fetchPokemons(@Query("limit") limit: Int, @Query("offset") offset: Int): PokemonListResponse

    @GET("pokemon/{id}")
    suspend fun fetchPokemon(@Path("id") pokemonId: Int): PokemonResponse
}