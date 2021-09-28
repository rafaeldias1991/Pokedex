package br.com.rafaeldias.apipokedex.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import br.com.rafaeldias.apipokedex.api.repository.ItemDaoRepository
import br.com.rafaeldias.apipokedex.api.repository.PokedexRepository
import br.com.rafaeldias.apipokedex.domain.Pokemon
import br.com.rafaeldias.apipokedex.utils.State
import java.lang.Exception


class HomeViewModel(
    private val idaov: ItemDaoRepository,
    private val pokedexRepository: PokedexRepository
) : ViewModel() {
    var pokemonData = MutableLiveData<List<Pokemon>>()

    init {
        loadItems()
        pokemonData = idaov.bringItems()

    }

    fun loadItems(){
       idaov.callListApi()
    }

    fun fetchPokemonsList() = liveData {
        emit(State.LoadingState)
        try {
            val response = pokedexRepository.fetchAllPokemons(153)
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(State.DataState(it.results))
                } ?: run {
                    emit(State.DataState(null))
                }
            }
        } catch (e: Exception) {
            emit(State.ErrorState(e))
        }
    }
}