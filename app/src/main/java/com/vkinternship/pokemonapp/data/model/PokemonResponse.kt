package com.vkinternship.pokemonapp.data.model

data class PokemonItem(
    val name: String,
    val url: String
)

data class Sprites(
    val front_default: String
)

data class PokemonResponse(
    val name: String,
    val sprites: Sprites,
)
data class PokemonListResponse(
    val results: List<PokemonItem>
)



