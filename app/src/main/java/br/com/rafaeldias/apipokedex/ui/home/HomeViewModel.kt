package br.com.rafaeldias.apipokedex.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.rafaeldias.apipokedex.api.repository.ItemDaoRepository
import br.com.rafaeldias.apipokedex.domain.Pokemon


class HomeViewModel(
    private val idaov: ItemDaoRepository,

) : ViewModel() {
    var pokemonData = MutableLiveData<List<Pokemon>>()

    init {
        loadItems()
        pokemonData = idaov.bringItems()

    }

    fun loadItems(){
       idaov.callListApi()
    }









}