package br.com.rafaeldias.apipokedex.api

class ApiUtils {
    companion object{
        val BASE_URL = "https://pokeapi.co/api/v2/"

        fun getItemsDaoInterface(): PokemonService {
            return RetrofitClient.getClient(BASE_URL)
                .create(PokemonService::class.java)
        }


    }
}