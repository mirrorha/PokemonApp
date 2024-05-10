package com.vkinternship.pokemonapp.ui.screens.pokemon

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle.Companion.Italic
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.vkinternship.pokemonapp.R
import com.vkinternship.pokemonapp.ui.component.ErrorScreen
import com.vkinternship.pokemonapp.ui.component.LoadingPokeball


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
    Surface(
        modifier = modifier
    ) {
        Column(
            modifier = modifier
        ) {
            when (uiState) {
                PokemonUiState.Loading -> LoadingPokeball(modifier = modifier)
                is PokemonUiState.Loaded -> {

                    val pokemon = uiState.pokemon

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
                    ) {
                        AsyncImage(
                            model = pokemon.defaultUrl,
                            contentDescription = null,
                            placeholder = painterResource(id = R.drawable.pokeball),
                            modifier = Modifier
                                .size(256.dp)
                                .align(Alignment.CenterHorizontally)
                                .clip(CircleShape)
                                //.background(MaterialTheme.colorScheme.background)
                                .padding(PaddingValues())
                        )

                        Spacer(modifier = Modifier.padding(16.dp))

                        Card(
                            colors = CardDefaults.cardColors(
                               containerColor = MaterialTheme.colorScheme.secondaryContainer
                            ),
                            shape = RoundedCornerShape(32.dp),
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxSize()
                        ) {
                            Column(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = pokemon.name,
                                    textAlign = TextAlign.Center,
                                    fontStyle = Italic,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 32.sp,
                                    modifier = Modifier
                                        .align(Alignment.CenterHorizontally)
                                        .padding(16.dp)
//                                .background(MaterialTheme.colorScheme.background)
                                )
                                HorizontalDivider(

                                    modifier = Modifier.padding(horizontal = 16.dp)
                                )

                            }

                        }

                    }
                }

                is PokemonUiState.Error -> ErrorScreen(error = uiState.error.toString())
            }
        }

    }

}