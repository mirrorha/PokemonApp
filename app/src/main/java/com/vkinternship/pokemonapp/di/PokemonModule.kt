package com.vkinternship.pokemonapp.di

import com.vkinternship.pokemonapp.data.OfflineFirstPokemonRepository
import com.vkinternship.pokemonapp.data.PokemonRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class PokemonModule {
    @Binds
    abstract fun bindsPokemonRepository(
        offlineFirstPokemonRepository: OfflineFirstPokemonRepository
    ): PokemonRepository

}