package br.com.rafaeldias.apipokedex.ui.home


import android.util.Log
import androidx.lifecycle.*
import br.com.rafaeldias.apipokedex.data.repository.PokedexRepository
import br.com.rafaeldias.apipokedex.ui.PokemonUI
import br.com.rafaeldias.apipokedex.ui.adapter.ApplySearchFilterName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class HomeViewModel(
    private val pokedexRepository: PokedexRepository,
    applySearchFilterName: ApplySearchFilterName

) : ViewModel() {

    private val _pokemonLiveData = MutableLiveData<List<PokemonUI>>()
    val pokemonLiveData: LiveData<List<PokemonUI>>
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
                } else {
                    _pokemonLiveData.postValue(result)
                }
            } catch (e: Exception) {
                Log.e("loadPokemon", e.toString())
            }
        }
    }

    fun updatePokemonFavorite(id: Int, favorite: Boolean) {
        viewModelScope.launch(Dispatchers.Default) {
            pokedexRepository.updateFavoritePokemon(id, favorite)
            _pokemonLiveData.value?.map {
                it.favorite = favorite
            }

        }
    }


    private val _searchQuery = MutableLiveData<CharSequence>("")
    val searchQuery: LiveData<CharSequence>
        get() = _searchQuery


    fun setSearchQuery(query: CharSequence?) {
        query?.let {
            _searchQuery.value = it
        }
    }

    val filteredListBusinessCard: LiveData<List<PokemonUI>> =
        applySearchFilterName.filterList(pokemonLiveData, searchQuery)


}