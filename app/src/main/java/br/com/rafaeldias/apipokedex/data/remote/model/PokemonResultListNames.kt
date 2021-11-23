package br.com.rafaeldias.apipokedex.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

data class PokemonsApiResult(
    val results: List<Pokemon>
)

data class Pokemon(
    val order: Int,
    val name: String,
    val url: String,
    val id: String = url,
    val stats:  List<Stat>,
    val types: List<Type>
)

data class Stat(
    val base_stat: Int,
    val effort: Int,
    val stat: StatX
)
data class StatX(
    val name: String,
    val url: String
)

data class Type(
    val Slot: Int,
    val type: TypeX
)
data class TypeX(
    val name: String,
    val url: String
)


