package br.com.rafaeldias.apipokedex.api.repository

import br.com.rafaeldias.apipokedex.ui.PokemonUI

interface Repository {
    suspend fun fetchAllPokemons() : Boolean
    suspend fun fetchAllPokemonsDb() : List<PokemonUI>
}