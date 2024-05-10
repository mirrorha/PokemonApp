package com.vkinternship.pokemonapp.ui.screens.pokemon

import android.util.Log
import androidx.annotation.MainThread
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vkinternship.pokemonapp.data.PokemonRepository
import com.vkinternship.pokemonapp.data.model.PokemonDetails
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed interface PokemonUiState {

    data object Loading : PokemonUiState

    data class Loaded(
        val pokemon: PokemonDetails
    ) : PokemonUiState

}

@HiltViewModel(assistedFactory = PokemonViewModel.Factory::class)
class PokemonViewModel @AssistedInject constructor(
    @Assisted private val pokemonId: Int,
    private val pokemonRepository: PokemonRepository
) : ViewModel() {

    private var initializeCalled = false

    private val _uiState: MutableStateFlow<PokemonUiState> = MutableStateFlow(PokemonUiState.Loading)

    val uiState = _uiState.asStateFlow()

    @MainThread
    fun initialize() {

        if (initializeCalled) return
        initializeCalled = true

        viewModelScope.launch {

            try {
                delay(2000)
                _uiState.value = PokemonUiState.Loaded(pokemonRepository.fetchPokemon(pokemonId))
            }

            catch (e: Exception) {
                Log.e("PokemonViewModel", "$e")
            }
        }

    }


    @AssistedFactory
    interface Factory {
        fun create(pokemonId: Int): PokemonViewModel
    }
}