package br.com.rafaeldias.apipokedex.domain

data class Pokemon (
    val number: Int,
    val name: String,
    val url: String,
    val id: String = url,
    val types: List<PokemonType>,
    )
{
    val formattedNumber = number.toString().padStart(3, '0')

    val imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$formattedNumber.png"
}
