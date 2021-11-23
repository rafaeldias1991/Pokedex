package br.com.rafaeldias.apipokedex.ui.adapter

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.map
import br.com.rafaeldias.apipokedex.ui.PokemonUI

class ApplySearchFilterName {
    fun filterList(
        list: LiveData<List<PokemonUI>>,
        searchQuery: LiveData<CharSequence>
    ): LiveData<List<PokemonUI>> =
        Transformations.switchMap(searchQuery) {
            list.map { list ->
                searchQuery.value?.let {
                    list.filter { pokemon ->
                        val query = searchQuery.value.toString()
                        with(pokemon) {
                            applyQuery(query)
                        }
                    }
                }
            }
        }

    private fun PokemonUI.applyQuery(query: String) =
                name.contains(query, true)

}