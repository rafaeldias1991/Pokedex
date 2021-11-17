package br.com.rafaeldias.apipokedex.data.local.model

import android.os.Parcelable
import androidx.room.*
import br.com.rafaeldias.apipokedex.data.model.Pokemon
import br.com.rafaeldias.apipokedex.data.remote.PokemonResultApi.ResultPokemonApi
import br.com.rafaeldias.apipokedex.ui.PokemonUI
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "pokemon_table")
data class PokemonEntity(
    val order: Int ,
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "poke_name")
    val name: String,
    val url: String = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${id}.png",
    val statsHp: Int,
    val statsAttack: Int,
    val statsDefense: Int,
    val statsSpeed: Int,
    val types1: String,
    val types2: String,
    @ColumnInfo(name ="favorite")
    val favorite: Boolean = false
): Parcelable


