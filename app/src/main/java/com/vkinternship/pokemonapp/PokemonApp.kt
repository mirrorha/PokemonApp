package com.vkinternship.pokemonapp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.vkinternship.pokemonapp.navigation.PokemonNavHost

@Composable
fun PokemonApp(
    pokemonAppState: PokemonAppState,
    modifier: Modifier = Modifier
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        PokemonNavHost(
            appState = pokemonAppState,
            modifier = modifier.padding(innerPadding)
        )
    }
}