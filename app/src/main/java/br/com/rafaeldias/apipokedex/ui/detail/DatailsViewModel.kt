package br.com.rafaeldias.apipokedex.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import br.com.rafaeldias.apipokedex.api.repository.Repository
import br.com.rafaeldias.apipokedex.utils.State


class DatailsViewModel(
    private val pokedexRepository: Repository
) : ViewModel() {

  /*  fun fetchPokemonsDetail(number: Int) = liveData {
        emit(State.LoadingState)
        try {
            val response = pokedexRepository.fetchDetailPokemons(number)
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(State.DataState(it))
                } ?: run {
                    emit(State.DataState(null))
                }
            }
        } catch (e: Exception) {
            emit(State.ErrorState(e))
        }
    }*/
}