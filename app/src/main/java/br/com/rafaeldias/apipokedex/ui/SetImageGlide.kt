package br.com.rafaeldias.apipokedex.ui

import android.widget.ImageView
import br.com.rafaeldias.apipokedex.R
import br.com.rafaeldias.apipokedex.databinding.ItenPokemonBinding
import br.com.rafaeldias.apipokedex.ui.adapter.PokemonAdapter
import com.bumptech.glide.Glide

fun ImageView.imageFromUrl(imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(this.context)
            .load(imageUrl)
           // .error(R.drawable.layer_placeholder)
           //.transition(DrawableTransitionOptions.withCrossFade())
            .into(this)
    }
}