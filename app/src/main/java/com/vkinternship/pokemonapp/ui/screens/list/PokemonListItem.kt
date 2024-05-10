package com.vkinternship.pokemonapp.ui.screens.list

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.vkinternship.pokemonapp.R
import com.vkinternship.pokemonapp.data.model.PokemonListItem
import java.util.Locale

@Composable
fun PokemonListItem(
    pokemon: PokemonListItem,
    onPokemonClicked: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = { onPokemonClicked(pokemon.id) },
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
        ) {
            AsyncImage(
                model = pokemon.url,
                contentDescription = null,
                placeholder = painterResource(R.drawable.pokeball),
                modifier = Modifier.size(128.dp)
            )
            Text(
                text = pokemon.name.uppercase(Locale.getDefault()),
                fontStyle = FontStyle.Italic,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.CenterVertically)
                    .fillMaxSize()
            )

        }
    }
}