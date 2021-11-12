package br.com.rafaeldias.apipokedex.data.repository

import br.com.rafaeldias.apipokedex.ui.PokemonUI

interface PokedexRepository {
    suspend fun fetchAllPokemons() : Boolean
    suspend fun fetchAllPokemonsDb() : List<PokemonUI>
}