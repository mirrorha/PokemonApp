package com.vkinternship.pokemonapp.navigation

sealed class Screens(val route: String) {
    data object PokemonList: Screens("pokemon-list")
    data object Pokemon: Screens("pokemon-screen")
}