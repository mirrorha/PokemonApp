package com.vkinternship.pokemonapp

import com.vkinternship.pokemonapp.data.OfflineFirstPokemonRepository
import com.vkinternship.pokemonapp.data.PokemonRepository
import com.vkinternship.pokemonapp.data.model.PokemonDetails
import com.vkinternship.pokemonapp.data.model.PokemonListItem
import com.vkinternship.pokemonapp.ui.screens.list.PokemonListUiState
import com.vkinternship.pokemonapp.ui.screens.list.PokemonListViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue


//class TestPokemonRepository: PokemonRepository {
//    override suspend fun fetchPokemons(): List<PokemonListItem> {
//        delay(1000)
//        return listOf(PokemonListItem(1, "1"), PokemonListItem(2, "2"))
//    }
//
//    override suspend fun fetchPokemonImageUrl(id: Int): String =
//        "url"
//
//    override suspend fun fetchPokemon(id: Int): PokemonDetails =
//        PokemonDetails("name", "defaultUrl")
//
//}
//
//class PokemonListViewModelUnitTest {
//
//    private val viewModel = PokemonListViewModel(
//        pokemonRepository = TestPokemonRepository()
//    )
//
//
//    @OptIn(ExperimentalCoroutinesApi::class)
//    @Test
//    fun `test pokemons loaded`() = runTest {
//
//        Dispatchers.setMain(UnconfinedTestDispatcher())
//        viewModel.initialize()
//        delay(10000)
//        assertEquals(viewModel.uiState.value , PokemonListUiState.Loaded(listOf()))
//
//
//    }
//    @Test
//    fun `test pokemons loading`() {
//
//    }
//    @Test
//    fun `test pokemons loading error`() {
//
//    }
//}