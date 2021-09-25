package br.com.rafaeldias.apipokedex.domain.response

data class Stat(
    val base_stat: Int,
    val effort: Int,
    val stat: StatX
)