package br.com.rafaeldias.apipokedex.utils

import android.content.Context
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import br.com.rafaeldias.apipokedex.R

class PokemonColor(var context: Context) {

    @ColorInt
    fun getTypeColor(type: String): Int {
        val color = when (type) {
            "normal" -> R.color.normal
            "fighting" -> R.color.fighting
            "action_bar" -> R.color.action_bar
            "flying" -> R.color.flying
            "poison" -> R.color.poison
            "ground" -> R.color.ground
            "rock" -> R.color.rock
            "bug" -> R.color.bug
            "ghost" -> R.color.ghost
            "steel" -> R.color.steel
            "fire" -> R.color.fire
            "water" -> R.color.water
            "grass" -> R.color.grass
            "electric" -> R.color.electric
            "psychic" -> R.color.psychic
            "ice" -> R.color.ice
            "dragon" -> R.color.dragon
            "fairy" -> R.color.fairy
            "dark" -> R.color.dark
        else -> R.color.white
        }

        return convertColor(color)

    }
    @ColorInt
    fun convertColor(@ColorRes color: Int): Int {
        return ContextCompat.getColor(context, color)
    }

}