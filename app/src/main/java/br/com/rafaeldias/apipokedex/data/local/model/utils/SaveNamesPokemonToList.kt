package br.com.rafaeldias.apipokedex.data.local.model.utils

import br.com.rafaeldias.apipokedex.data.local.model.PokemonEntity
import br.com.rafaeldias.apipokedex.data.local.model.toPokemonEntity
import br.com.rafaeldias.apipokedex.data.remote.PokemonResultApi.ResultPokemonApi


fun saveNamesPokemonToList(list: List<ResultPokemonApi>): List<PokemonEntity> {
    val namesPokemon = arrayListOf<PokemonEntity>()
    list.forEach {
        namesPokemon.add(it.toPokemonEntity())
    }
    return namesPokemon
}