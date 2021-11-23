package br.com.rafaeldias.apipokedex.data.remote.PokemonResultApi

data class Ability(
    val ability: AbilityX,
    val is_hidden: Boolean,
    val slot: Int
)