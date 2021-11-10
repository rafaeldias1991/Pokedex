package br.com.rafaeldias.apipokedex.ui.home

import android.util.Log
import androidx.lifecycle.*
import br.com.rafaeldias.apipokedex.data.repository.Repository
import br.com.rafaeldias.apipokedex.ui.PokemonUI
import kotlinx.coroutines.Dispatchers
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
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = pokedexRepository.fetchAllPokemonsDb()
                if (result.isEmpty()) {
                        val result = pokedexRepository.fetchAllPokemons()
                        if (result == true) {
                            _pokemonLiveData.postValue(pokedexRepository.fetchAllPokemonsDb())
                        }
                    }else{
                    _pokemonLiveData.postValue(result)
                }

            } catch (e: Exception) {
                Log.e("loadPokemon", e.toString())
            }
        }
    }


}