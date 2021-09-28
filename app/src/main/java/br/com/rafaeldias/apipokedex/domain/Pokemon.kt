package br.com.rafaeldias.apipokedex.domain

import br.com.rafaeldias.apipokedex.domain.response.Stat
import br.com.rafaeldias.apipokedex.domain.response.Type

data class Pokemon (
    val order: Int,
    val name: String,
    val url: String,
    val id: String = url,
    val stats: List<Stat>,
    val types: List<Type>
    )
{
    val formattedNumber = order.toString().padStart(3, '0')

}
