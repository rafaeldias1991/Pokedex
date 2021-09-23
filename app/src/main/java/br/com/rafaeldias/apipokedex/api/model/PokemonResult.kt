package br.com.rafaeldias.apipokedex.api.model

import br.com.rafaeldias.apipokedex.domain.Pokemon
import br.com.rafaeldias.apipokedex.domain.PokemonType

data class PokemonsApiResult(
    val results: List<Pokemon>
)

data class PokemonResult(
    val name: String,
    val url: String
)

data class PokemonApiResult(
    val id: Int,
    val name: String,

)

data class PokemonTypeSlot(
    val slot: Int,
    val type: PokemonType
)