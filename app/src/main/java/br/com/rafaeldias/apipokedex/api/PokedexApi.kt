package br.com.rafaeldias.apipokedex.api

import br.com.rafaeldias.apipokedex.api.model.PokemonInfo
import br.com.rafaeldias.apipokedex.api.model.PokemonsApiResult
import br.com.rafaeldias.apipokedex.domain.Pokemon
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokedexApi {

    @GET("pokemon")
    suspend fun listPokemons(@Query("limit") limit: Int): Response<PokemonsApiResult>

    @GET("pokemon/{number}")
    fun getPokemon(@Path("number") number: Int): Response<Pokemon>

    @GET("pokemon/{name}")
    fun getPokemonName(@Path("name") name: String): Response<PokemonInfo>
}