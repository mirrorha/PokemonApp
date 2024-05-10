package com.vkinternship.pokemonapp.ui.screens.list

import android.util.Log
import androidx.annotation.MainThread
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vkinternship.pokemonapp.data.PokemonRepository
import com.vkinternship.pokemonapp.data.model.PokemonListItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


private const val TAG= "PokemonListViewModel"

sealed interface PokemonListUiState {
    data object Loading : PokemonListUiState
    data class Loaded(
        val pokemons: List<PokemonListItem>
    ) : PokemonListUiState
}

private fun List<PokemonListItem>.updateUrl(index: Int, value: String): List<PokemonListItem> {
    val pokemon = this[index]

    val newPokemon = PokemonListItem(
        id = pokemon.id,
        name = pokemon.name,
        url = value
    )

    val list = this.toMutableList().also { it[index] = newPokemon }
    return list
}

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository
) : ViewModel() {
    private var initializeCalled = false

    private val _uiState: MutableStateFlow<PokemonListUiState> = MutableStateFlow(PokemonListUiState.Loading)
    val uiState: StateFlow<PokemonListUiState> = _uiState.asStateFlow()

    @MainThread
    fun initialize() {

        if(initializeCalled) return
        initializeCalled = true


        viewModelScope.launch {

            try {
                val pokemons = pokemonRepository.fetchPokemons()

                _uiState.value = PokemonListUiState.Loaded(pokemons)

                pokemons.forEachIndexed { index, pokemon ->

                    launch {
                        delay(2000)
                        val url = pokemonRepository.fetchPokemonImageUrl(pokemon.id)

                        _uiState.update {
                            (it as PokemonListUiState.Loaded).copy(
                                pokemons = it.pokemons.updateUrl(index, url)
                            )
                        }
                    }
                }
            }

            catch (e: Exception) {
                Log.e(TAG, "$e")
            }
        }
    }
}
