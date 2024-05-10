package com.vkinternship.pokemonapp.ui.screens.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vkinternship.pokemonapp.ui.component.ErrorScreen
import com.vkinternship.pokemonapp.ui.component.LoadingPokeball
import com.vkinternship.pokemonapp.ui.theme.PokemonAppTheme

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
        modifier = modifier
    ) {

        when(uiState) {
            PokemonListUiState.Loading ->
                LoadingPokeball(modifier = modifier)

            is PokemonListUiState.Loaded -> {

                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(horizontal = 8.dp),
                    modifier = modifier
                ) {
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

            is PokemonListUiState.Error -> ErrorScreen(error = uiState.error.toString())
        }
    }
}

@Preview
@Composable
fun PokemonListLoadingPreview(modifier: Modifier = Modifier) {

    PokemonAppTheme {
        PokemonListScreen(
            uiState = PokemonListUiState.Loading,
            onPokemonClicked = {}
        )
    }

}
