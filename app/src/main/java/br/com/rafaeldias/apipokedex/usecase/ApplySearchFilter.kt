package br.com.rafaeldias.apipokedex.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.map
import br.com.rafaeldias.apipokedex.api.model.PokemonsApiResult
import br.com.rafaeldias.apipokedex.domain.Pokemon
import retrofit2.http.Query

class ApplySearchFilter {

    fun filterList(
        list: LiveData<List<Pokemon>>,
        searchQuery: LiveData<CharSequence>
    ):LiveData<List<Pokemon>> =
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

    private fun Pokemon.applyQuery(query: String) =
        name.contains(query,true)
}