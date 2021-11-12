package br.com.rafaeldias.apipokedex.utils

import java.util.*

fun formatTitle(name: String): String {
    val names = name.replaceFirstChar {
            it.titlecase(Locale.getDefault())
    }
    return names

}