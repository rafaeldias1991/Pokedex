package br.com.rafaeldias.apipokedex.api.model

import br.com.rafaeldias.apipokedex.domain.Pokemon
import br.com.rafaeldias.apipokedex.domain.response.Stat
import br.com.rafaeldias.apipokedex.domain.response.Type

data class PokemonsApiResult(
    val results: List<Pokemon>
)
data class PokemonInfo(
    val stats: List<Stat>,
    val types: List<Type>
)
