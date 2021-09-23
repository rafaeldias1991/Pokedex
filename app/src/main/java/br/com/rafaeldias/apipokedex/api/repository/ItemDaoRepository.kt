package br.com.rafaeldias.apipokedex.api.repository

import androidx.lifecycle.MutableLiveData
import br.com.rafaeldias.apipokedex.api.ApiUtils
import br.com.rafaeldias.apipokedex.api.RetrofitClient
import br.com.rafaeldias.apipokedex.api.PokemonService
import br.com.rafaeldias.apipokedex.api.model.PokemonsApiResult
import br.com.rafaeldias.apipokedex.domain.Pokemon
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ItemDaoRepository {
    private val listPokemon: MutableLiveData<List<Pokemon>> = MutableLiveData()
    private val idao: PokemonService = ApiUtils.getItemsDaoInterface()

    fun bringItems(): MutableLiveData<List<Pokemon>> {
        return listPokemon
    }


    fun callListApi(){
            idao.listPokemons(153)
            .enqueue(object : Callback<PokemonsApiResult> {
                override fun onResponse(
                    call: Call<PokemonsApiResult>,
                    response: Response<PokemonsApiResult>
                ) {
                    listPokemon.postValue(response.body()?.results)
                }

                override fun onFailure(call: Call<PokemonsApiResult>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })

    }

}