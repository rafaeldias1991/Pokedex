package br.com.rafaeldias.apipokedex.api.repository

import br.com.rafaeldias.apipokedex.api.PokedexApi
import br.com.rafaeldias.apipokedex.api.model.PokemonsApiResult
import retrofit2.Response

class PokedexRepository(private val api: PokedexApi) {

    suspend fun fetchAllPokemons(limit: Int): Response<PokemonsApiResult> {
        return api.listPokemons(limit)
    }
}