package br.com.rafaeldias.apipokedex.api

import br.com.rafaeldias.apipokedex.api.model.PokemonInfo
import br.com.rafaeldias.apipokedex.api.model.PokemonsApiResult
import br.com.rafaeldias.apipokedex.domain.Pokemon
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonService {

    @GET("pokemon")
    fun listPokemons(@Query("limit") limit: Int): Call<PokemonsApiResult>

    @GET("pokemon/{number}")
    fun getPokemon(@Path("number") number: Int): Call<Pokemon>

    @GET("pokemon/{name}")
    fun getPokemonName(@Path("name") name: String): Call<PokemonInfo>
}