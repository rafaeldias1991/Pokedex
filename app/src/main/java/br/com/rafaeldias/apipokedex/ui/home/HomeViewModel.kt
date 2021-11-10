package br.com.rafaeldias.apipokedex.ui.home

import android.util.Log
import androidx.lifecycle.*
import br.com.rafaeldias.apipokedex.api.repository.Repository
import br.com.rafaeldias.apipokedex.ui.PokemonUI
import br.com.rafaeldias.apipokedex.utils.State
import com.skydoves.progressview.progressView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class HomeViewModel(
    private val pokedexRepository: Repository,
) : ViewModel() {

    private val _pokemonLiveData: MutableLiveData<List<PokemonUI>> = MutableLiveData()
    val pokemonLiveData: MutableLiveData<List<PokemonUI>>
        get() = _pokemonLiveData

    init {
        loadPokemon()
    }

    fun loadPokemon() {
        viewModelScope.launch(Dispatchers.Default) {
            pokedexRepository.fetchAllPokemons()
            _pokemonLiveData.postValue(pokedexRepository.fetchAllPokemonsDb())

        }
    }


}