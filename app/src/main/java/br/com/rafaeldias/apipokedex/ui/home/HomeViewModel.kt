package br.com.rafaeldias.apipokedex.ui.home

<<<<<<< HEAD
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.rafaeldias.apipokedex.api.repository.ItemDaoRepository
import br.com.rafaeldias.apipokedex.domain.Pokemon


class HomeViewModel(
    private val idaov: ItemDaoRepository,

=======
import android.util.Log
import androidx.lifecycle.*
import br.com.rafaeldias.apipokedex.data.repository.PokedexRepository
import br.com.rafaeldias.apipokedex.ui.PokemonUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class HomeViewModel(
    private val pokedexRepository: PokedexRepository,
>>>>>>> master
) : ViewModel() {

    private val _pokemonLiveData: MutableLiveData<List<PokemonUI>> = MutableLiveData()
    val pokemonLiveData: LiveData<List<PokemonUI>>
        get() = _pokemonLiveData

    init {
        loadPokemon()
    }

<<<<<<< HEAD






=======
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
>>>>>>> master


}