package br.com.rafaeldias.apipokedex.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.rafaeldias.apipokedex.api.repository.ItemDaoRepository
import br.com.rafaeldias.apipokedex.domain.Pokemon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DatailsViewModel(
    private val idaod: ItemDaoRepository
) : ViewModel() {

    var pokemonDataDetail = MutableLiveData<Pokemon>()

    init {
        pokemonDataDetail = idaod.bringInfoPoke()
    }
    fun loadPokemonDetail(number: Int){
        viewModelScope.launch(Dispatchers.IO) {
            idaod.pokemonDetails(number) }

    }
}