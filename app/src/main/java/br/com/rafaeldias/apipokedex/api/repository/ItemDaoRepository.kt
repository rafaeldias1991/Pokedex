package br.com.rafaeldias.apipokedex.api.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import br.com.rafaeldias.apipokedex.api.ApiUtils
import br.com.rafaeldias.apipokedex.api.PokemonService
import br.com.rafaeldias.apipokedex.api.model.PokemonsApiResult
import br.com.rafaeldias.apipokedex.domain.Pokemon
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ItemDaoRepository {
    private val listPokemon: MutableLiveData<List<Pokemon>> = MutableLiveData()
    private val idao: PokemonService = ApiUtils.getItemsDaoInterface()
    private val pokeDetails: MutableLiveData<Pokemon> = MutableLiveData()


    fun bringItems(): MutableLiveData<List<Pokemon>> {
        return listPokemon
    }
    fun bringInfoPoke(): MutableLiveData<Pokemon> {
        return pokeDetails
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
                    Log.d("TAG", "Erro")
                }
            })
    }

    fun pokemonDetails (id: Int){
        idao.getPokemon(id)
            .enqueue(object : Callback<Pokemon> {
                override fun onResponse(
                    call: Call<Pokemon>,
                    response: Response<Pokemon>
                ) {
                    pokeDetails.postValue(response.body())
                }
                override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                    Log.d("TAG", "Erro")
                }
            })
    }
}