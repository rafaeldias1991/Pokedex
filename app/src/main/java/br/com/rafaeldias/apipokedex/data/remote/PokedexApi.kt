package br.com.rafaeldias.apipokedex.data.remote

import br.com.rafaeldias.apipokedex.data.model.PokemonsApiResult
import br.com.rafaeldias.apipokedex.data.model.Pokemon
import br.com.rafaeldias.apipokedex.data.remote.PokemonResultApi.ResultPokemonApi
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokedexApi {

    @GET("pokemon")
    suspend fun listPokemons(@Query("limit") limit: Int): Response<PokemonsApiResult>

    @GET("pokemon/{name}")
    suspend fun getPokemon(@Path("name") number: String): Response<ResultPokemonApi>

    @GET("pokemon/{name}")
    suspend fun getPokemonName(@Query("limit") limit: Int,@Path("name") name: String): List<Pokemon>
}