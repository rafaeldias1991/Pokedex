package br.com.rafaeldias.apipokedex.data.repository

import android.util.Log
import br.com.rafaeldias.apipokedex.data.local.PokemonDataSource
import br.com.rafaeldias.apipokedex.data.model.Pokemon
import br.com.rafaeldias.apipokedex.data.remote.PokedexApi
import br.com.rafaeldias.apipokedex.data.remote.PokemonResultApi.ResultPokemonApi
import br.com.rafaeldias.apipokedex.ui.PokemonUI
import br.com.rafaeldias.apipokedex.utils.AppConstants.QUANTITY_POKEMON
import kotlinx.coroutines.flow.first

class PokedexRepositoryImpl(
    private val api: PokedexApi,
    private val pokemonDataSource: PokemonDataSource
) : Repository {

    override suspend fun fetchAllPokemonsDb(): List<PokemonUI> {
        Log.e("resultdblistsss", pokemonDataSource.getPokemons().toString())
        return pokemonDataSource.getPokemons()

    }

    override suspend fun fetchAllPokemons(): Boolean {
        try {
            val result = api.listPokemons(QUANTITY_POKEMON)
            Log.e("resultapi", result.toString())
            if (result.isSuccessful) {
                getDetail(result.body()?.results)
            }
            return true
        } catch (e: Throwable) {
            return false
        }

    }

    suspend fun getDetail(list: List<Pokemon>?) {
        val names = arrayListOf<ResultPokemonApi>()
        val name = arrayListOf<String>()

        list?.forEach {
            name.add(it.name)
        }
        Log.e("name_list", name.toString())

        name.forEach {

            try {
                val result = api.getPokemon(it)
                if (result.isSuccessful) {
                    names.add(result.body()!!)
                }
                Log.e("names_list", names.toString())

            } catch (e: Exception) {
                Log.e("api.getpokemon", e.toString())
            }

        }
        pokemonDataSource.insertPokemons(names.toList())
    }

}