package br.com.rafaeldias.apipokedex.ui

data class PokemonUI(
    val order: Int = 0,
    val name: String = "",
    val id: Int = 0,
    val urlImg: String = "",
    val statsHp: Int = 0,
    val statsAttack: Int = 0,
    val statsDefense: Int = 0,
    val statsSpeed: Int = 0,
    val types1: String = "",
    val types2: String = "",
    var favorite: Boolean = false
){
     val formattedNumber = "Nº "+id.toString().padStart(3, '0')
}
