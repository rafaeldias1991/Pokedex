package br.com.rafaeldias.apipokedex.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.rafaeldias.apipokedex.api.repository.ItemDaoRepository
import br.com.rafaeldias.apipokedex.domain.Pokemon


class DatailsViewModel(
    private val idaod: ItemDaoRepository
) : ViewModel() {

    var pokemonDataDetail = MutableLiveData<Pokemon>()


    fun loadPokemonDetail(number: Int){
        idaod.pokemonDetails(number)
        pokemonDataDetail = idaod.bringInfoPoke()

    }
}