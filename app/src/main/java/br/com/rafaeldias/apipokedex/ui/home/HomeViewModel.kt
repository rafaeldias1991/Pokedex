package br.com.rafaeldias.apipokedex.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.rafaeldias.apipokedex.api.repository.ItemDaoRepository
import br.com.rafaeldias.apipokedex.domain.Pokemon


class HomeViewModel(
    private val idaov: ItemDaoRepository,

) : ViewModel() {
    var pokemonData = MutableLiveData<List<Pokemon>>()

    var pokemonDataDetail = MutableLiveData<Pokemon>()


    init {
        loadItems()
    }



    fun loadPokemonDetail(number: Int){
        idaov.pokemonDetails(number)
        pokemonDataDetail = idaov.bringInfoPoke()
        Log.d("caregade","caregadodeta")

    }

    fun loadItems(){
       idaov.callListApi()
        pokemonData = idaov.bringItems()
        Log.d("carega","caregado")

    }









}