package br.com.rafaeldias.apipokedex.ui.Utils

import androidx.cardview.widget.CardView
import br.com.rafaeldias.apipokedex.utils.PokemonColor

fun CardView.colorCardView(type: String){
    val context = getContext()
    val color = PokemonColor(context).getTypeColor(type)
    setCardBackgroundColor(color)
}