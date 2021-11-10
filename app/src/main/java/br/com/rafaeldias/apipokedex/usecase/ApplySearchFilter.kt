package br.com.rafaeldias.apipokedex.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.map
import br.com.rafaeldias.apipokedex.data.local.model.PokemonEntity

class ApplySearchFilter {

    fun filterList(
        list: LiveData<List<PokemonEntity>>,
        searchQuery: LiveData<CharSequence>
    ):LiveData<List<PokemonEntity>> =
        Transformations.switchMap(searchQuery){
            list.map { list->
                searchQuery.value?.let {
                    list.filter { pokemon ->
                        val query = searchQuery.value.toString()
                        with(pokemon){
                            applyQuery(query)
                        }
                    }
                }
            }
        }

    private fun PokemonEntity.applyQuery(query: String) =
        name.contains(query,true)
}