package br.com.rafaeldias.apipokedex.data.remote.PokemonResultApi

data class Move(
    val move: MoveX,
    val version_group_details: List<VersionGroupDetail>
)