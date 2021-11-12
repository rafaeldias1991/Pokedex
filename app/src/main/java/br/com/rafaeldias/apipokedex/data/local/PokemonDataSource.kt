package br.com.rafaeldias.apipokedex.data.local

import br.com.rafaeldias.apipokedex.data.local.model.PokemonEntity
import br.com.rafaeldias.apipokedex.data.model.Pokemon
import br.com.rafaeldias.apipokedex.data.remote.PokemonResultApi.ResultPokemonApi
import br.com.rafaeldias.apipokedex.ui.PokemonUI
import kotlinx.coroutines.flow.Flow

interface PokemonDataSource {

    suspend fun getPokemons():List<PokemonUI>
    fun insertPokemons(list: List<ResultPokemonApi>)
    fun updatePokemon(pokemonEntity: PokemonEntity)


}