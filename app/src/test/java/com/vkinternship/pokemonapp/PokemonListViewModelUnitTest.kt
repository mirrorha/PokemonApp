package com.vkinternship.pokemonapp

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
import kotlinx.coroutines.withContext
import org.junit.Before
import kotlin.test.Test
import kotlin.test.assertTrue


open class TestPokemonRepository: PokemonRepository {

    override suspend fun fetchPokemons(): List<PokemonListItem> {

        withContext(Dispatchers.Default) {
            delay(1000)
        }

        return listOf(PokemonListItem(1, "1"), PokemonListItem(2, "2"))
    }

    override suspend fun fetchPokemonImageUrl(id: Int): String =
        "url"

    override suspend fun fetchPokemon(id: Int): PokemonDetails =
        PokemonDetails("name", "defaultUrl")

}

class TestErrorPokemonRepository: TestPokemonRepository() {

    override suspend fun fetchPokemons(): List<PokemonListItem> {
        throw Exception("Error")
        return super.fetchPokemons()
    }

}

class PokemonListViewModelUnitTest {

    private val testPokemonRepository = TestPokemonRepository()

    private lateinit var viewModel: PokemonListViewModel
    @Before
    fun setup() {
        viewModel = PokemonListViewModel(testPokemonRepository)
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test pokemons loaded`() = runTest {

        Dispatchers.setMain(UnconfinedTestDispatcher())

        withContext(Dispatchers.Default) {
            viewModel.initialize()
            delay(3000)
        }

        assertTrue(viewModel.uiState.value is PokemonListUiState.Loaded)

    }
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test pokemons loading`() = runTest {
        Dispatchers.setMain(UnconfinedTestDispatcher())

        withContext(Dispatchers.Default) {
            viewModel.initialize()
        }

        assertTrue(viewModel.uiState.value is PokemonListUiState.Loading)
    }
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test pokemons loading error`() = runTest {

        Dispatchers.setMain(UnconfinedTestDispatcher())

        val errorViewModel = PokemonListViewModel(TestErrorPokemonRepository())

        errorViewModel.initialize()

        assertTrue(errorViewModel.uiState.value is PokemonListUiState.Error)
    }
}