package com.vkinternship.pokemonapp.ui.screens.pokemon

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.vkinternship.pokemonapp.R


@Composable
fun PokemonRoute(
    viewModel: PokemonViewModel
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(null) {
        viewModel.initialize()
    }

    PokemonScreen(uiState)
}
@Composable
fun PokemonScreen(
    uiState: PokemonUiState,
    modifier: Modifier = Modifier
) {
    
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        when (uiState) {
            PokemonUiState.Loading -> Text(text = "LOADING")
            is PokemonUiState.Loaded -> {

                val pokemon = uiState.pokemon

                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    AsyncImage(
                        model = pokemon.defaultUrl,
                        contentDescription = null,
                        placeholder = painterResource(id = R.drawable.pokeball),
                        modifier = Modifier
                            .size(512.dp)
                            .align(Alignment.CenterHorizontally)
                    )

                    Text(
                        text = pokemon.name,
                        textAlign = TextAlign.Center,
                        fontSize = 32.sp,
                        modifier = Modifier
                            .fillMaxSize()
                            .align(Alignment.CenterHorizontally)
                        )

                }
            }
        }
    }

}