package br.com.rafaeldias.apipokedex.utils

import android.widget.ImageButton
import android.widget.ImageView
import br.com.rafaeldias.apipokedex.R

fun ImageButton.setImageButtonFavorite(pokemon: Boolean){
    if (pokemon) {
        setImageResource(R.drawable.pokeball_close)
    }else setImageResource(R.drawable.pokeball_open)
}

fun ImageView.setImageFavorite(favorite: Boolean){
    if (favorite){
        setImageResource(R.drawable.pokeball_close)
    }else setImageResource(R.drawable.pokeball_open)
}