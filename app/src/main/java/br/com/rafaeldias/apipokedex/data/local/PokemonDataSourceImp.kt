package br.com.rafaeldias.apipokedex.data.local

import android.util.Log
import br.com.rafaeldias.apipokedex.data.local.model.PokemonEntity
import br.com.rafaeldias.apipokedex.data.local.model.toPokemon
import br.com.rafaeldias.apipokedex.data.local.model.toPokemonEntity
import br.com.rafaeldias.apipokedex.data.model.Pokemon
import br.com.rafaeldias.apipokedex.data.remote.PokemonResultApi.ResultPokemonApi
import br.com.rafaeldias.apipokedex.ui.PokemonUI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class PokemonDataSourceImp(private val pokeDAO: PokeDAO) : PokemonDataSource {

    suspend override fun getPokemons(): Flow<List<PokemonUI>> {
        val result : Flow<List<PokemonUI>> =  flow {
            val result = pokeDAO.getAllPokemon().toPokemon()
            emit(result)
        }
        return result

    }

    override fun insertPokemons(list: List<ResultPokemonApi>){
        val names = arrayListOf<PokemonEntity>()
        list.forEach {
           names.add(it.toPokemonEntity())
        }
        pokeDAO.saveAllPokemon(names)
    }

    override fun updatePokemon(pokemonEntity: PokemonEntity) {
        pokeDAO.updatePokemon(pokemonEntity)
    }
}

