package br.com.rafaeldias.apipokedex.ui.detail

import androidx.lifecycle.ViewModel
import br.com.rafaeldias.apipokedex.data.repository.Repository


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