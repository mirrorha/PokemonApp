package com.vkinternship.pokemonapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.vkinternship.pokemonapp.navigation.PokemonNavHost

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonApp(
    pokemonAppState: PokemonAppState,
    modifier: Modifier = Modifier
) {

    Scaffold(
        topBar =  {
            TopAppBar(
                title =  { Text(text = "Pokemon App") },
                ) },
        modifier = modifier

    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            PokemonNavHost(
                appState = pokemonAppState,
            )
        }

    }
}