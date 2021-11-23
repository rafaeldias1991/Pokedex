package br.com.rafaeldias.apipokedex.data.local

import android.util.Log
import br.com.rafaeldias.apipokedex.data.local.model.PokemonEntity
import br.com.rafaeldias.apipokedex.data.local.model.toPokemonEntity
import br.com.rafaeldias.apipokedex.data.local.model.toPokemonUI
import br.com.rafaeldias.apipokedex.data.local.model.utils.saveNamesPokemonToList
import br.com.rafaeldias.apipokedex.data.remote.PokemonResultApi.ResultPokemonApi
import br.com.rafaeldias.apipokedex.ui.PokemonUI

class PokemonDataSourceImp(private val pokeDAO: PokeDAO) : PokemonDataSource {

    suspend override fun getPokemons(): List<PokemonUI> {
        val result = pokeDAO.getAllPokemon()
        return result.toPokemonUI()
    }

    override fun insertPokemons(list: List<ResultPokemonApi>) {
        try {
            pokeDAO.saveAllPokemon(saveNamesPokemonToList(list))
        } catch (e: Exception) {
            Log.e("insertError", e.toString())
        }
    }

    override suspend fun updatePokemon(id : Int, favorite: Boolean) {
        pokeDAO.updatePokemon(id,favorite)
    }
}


