package com.vkinternship.pokemonapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.vkinternship.pokemonapp.PokemonAppState
import com.vkinternship.pokemonapp.ui.screens.list.PokemonListRoute
import com.vkinternship.pokemonapp.ui.screens.pokemon.PokemonRoute
import com.vkinternship.pokemonapp.ui.screens.pokemon.PokemonViewModel

@Composable
fun PokemonNavHost(
    appState: PokemonAppState,
    modifier: Modifier = Modifier
) {
    val navController = appState.navController

    NavHost(
        navController = navController,
        startDestination = Screens.PokemonList.route,
        modifier = modifier
    ) {
        composable(
            route = Screens.PokemonList.route
        ) {
            PokemonListRoute {  id ->
                navController.navigate("${Screens.Pokemon.route}/$id")
            }
        }

        composable(
            route = "${Screens.Pokemon.route}/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) {

            navBackStackEntry ->
                val id = navBackStackEntry.arguments?.getInt("id")!!

            PokemonRoute(
                viewModel = hiltViewModel { factory: PokemonViewModel.Factory ->
                    factory.create(id)
                }
            )
        }
    }
}