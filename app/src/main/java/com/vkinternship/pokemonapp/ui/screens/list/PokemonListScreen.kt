package com.vkinternship.pokemonapp.ui.screens.list

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.vkinternship.pokemonapp.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.coroutineScope
import java.util.Locale

private const val TAG = "PokemonListScreen"

@Composable
fun PokemonListRoute(
    viewModel: PokemonListViewModel = hiltViewModel(),
    onPokemonClicked: (id: Int) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(null) {
        viewModel.initialize()
    }

    PokemonListScreen(
        uiState = uiState,
        onPokemonClicked = onPokemonClicked
    )
}
@Composable
fun PokemonListScreen(
    uiState: PokemonListUiState,
    onPokemonClicked: (id: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {

        when(uiState) {
            PokemonListUiState.Loading ->
                Text(text = "LOADING")

            is PokemonListUiState.Loaded -> {

                LazyColumn {
                    items(
                        items = uiState.pokemons,
                        key = { it.id }
                    ) { pokemon ->
                        PokemonListItem(
                            pokemon = pokemon,
                            onPokemonClicked = onPokemonClicked
                        )
                    }
                }
            }
        }
    }
}
