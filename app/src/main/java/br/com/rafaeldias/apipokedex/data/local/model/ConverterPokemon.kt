package br.com.rafaeldias.apipokedex.data.local.model

import br.com.rafaeldias.apipokedex.data.remote.PokemonResultApi.ResultPokemonApi
import br.com.rafaeldias.apipokedex.ui.PokemonUI

fun ResultPokemonApi.toPokemonEntity() : PokemonEntity {
    return with(this){
        PokemonEntity(
            order = this.id,
            name = this.name,
            id = this.id,
            types1 = this.types[0].type.name,
            types2 = this.types[0].type.name,
            statsHp = this.stats[0].base_stat,
            statsAttack = this.stats[1].base_stat,
            statsDefense = this.stats[2].base_stat,
            statsSpeed = this.stats[3].base_stat)
    }
}


fun List<PokemonEntity>.toPokemonUI(): List<PokemonUI> = this.map{
    PokemonUI(
        order = it.order,
        name = it.name,
        id = it.id,
        urlImg = it.url,
        types1 = it.types1,
        types2 = it.types2,
        statsHp = it.statsHp,
        statsAttack = it.statsAttack,
        statsDefense = it.statsDefense,
        statsSpeed = it.statsSpeed
    )
}