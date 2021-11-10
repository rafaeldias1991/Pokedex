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
    val name: String,
    val statsHp: Int,
    val statsAttack: Int,
    val statsDefense: Int,
    val statsSpeed: Int,
    val types1: String,
    val types2: String

): Parcelable

fun ResultPokemonApi.toPokemonEntity() : PokemonEntity {
     return with(this){
         PokemonEntity(
             order = this.order,
             name = this.name,
             types1 = this.types[0].type.name,
             types2 = this.types[0].type.name,
             statsHp = this.stats[0].base_stat,
             statsAttack = this.stats[1].base_stat,
             statsDefense = this.stats[2].base_stat,
             statsSpeed = this.stats[3].base_stat)
     }
}


fun List<PokemonEntity>.toPokemon(): List<PokemonUI> = this.map{
    PokemonUI(
        order = it.order,
        name = it.name,
        types1 = it.types1,
        types2 = it.types2,
        statsHp = it.statsHp,
        statsAttack = it.statsAttack,
        statsDefense = it.statsDefense,
        statsSpeed = it.statsSpeed
    )
}

