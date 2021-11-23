package br.com.rafaeldias.apipokedex.data.repository

import android.util.Log
import br.com.rafaeldias.apipokedex.data.local.PokemonDataSource
import br.com.rafaeldias.apipokedex.data.model.Pokemon
import br.com.rafaeldias.apipokedex.data.remote.PokedexApi
import br.com.rafaeldias.apipokedex.data.remote.PokemonResultApi.ResultPokemonApi
import br.com.rafaeldias.apipokedex.ui.PokemonUI
import br.com.rafaeldias.apipokedex.utils.AppConstants.QUANTITY_POKEMON

class PokedexRepositoryImpl(
    private val api: PokedexApi,
    private val pokemonDataSource: PokemonDataSource
) : PokedexRepository {

    override suspend fun fetchAllPokemonsDb(): List<PokemonUI> {
        Log.e("resultdblist_repository", pokemonDataSource.getPokemons().toString())
        return pokemonDataSource.getPokemons()

    }

    override suspend fun updateFavoritePokemon(id:Int,favorite:Boolean){
       pokemonDataSource.updatePokemon(id,favorite)
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
        val listPokemons = arrayListOf<ResultPokemonApi>()
        val allNamesPokemons = arrayListOf<String>()
        list?.forEach {
            allNamesPokemons.add(it.name)
        }
        allNamesPokemons.forEach {
            try {
                val result = api.getPokemon(it)
                val resultBody = result.body()
                if (result.isSuccessful) {
                    listPokemons.add(result.body()!!)
                }
                Log.e("names_list", resultBody!!.name)

            } catch (e: Exception) {
                Log.e("api.getpokemon", e.toString())
            }
        }
        pokemonDataSource.insertPokemons(listPokemons.toList())
    }

}