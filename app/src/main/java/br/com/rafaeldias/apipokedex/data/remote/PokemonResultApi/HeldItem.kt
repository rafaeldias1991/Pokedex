package br.com.rafaeldias.apipokedex.data.remote.PokemonResultApi

data class HeldItem(
    val item: Item,
    val version_details: List<VersionDetail>
)