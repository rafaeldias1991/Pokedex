package br.com.rafaeldias.apipokedex.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.rafaeldias.apipokedex.api.repository.ItemDaoRepository
import br.com.rafaeldias.apipokedex.domain.Pokemon
import br.com.rafaeldias.apipokedex.usecase.ApplySearchFilter


class HomeViewModel(
    private val idaov: ItemDaoRepository,
    private val applySearchFilter: ApplySearchFilter

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