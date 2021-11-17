package br.com.rafaeldias.apipokedex.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.rafaeldias.apipokedex.databinding.ItenPokemonBinding
import br.com.rafaeldias.apipokedex.ui.PokemonUI
import br.com.rafaeldias.apipokedex.ui.Utils.colorCardView
import br.com.rafaeldias.apipokedex.ui.home.HomeFragmentDirections
import br.com.rafaeldias.apipokedex.ui.imageFromUrl
import br.com.rafaeldias.apipokedex.utils.formatTitle
import br.com.rafaeldias.apipokedex.utils.setImageFavorite

class PokemonAdapter: ListAdapter<PokemonUI, PokemonAdapter.ViewHolder>(PokemonUICallBack()){
    protected lateinit var binding: ItenPokemonBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = ItenPokemonBinding
            .inflate(layoutInflater,parent, false
            )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val item = getItem(position)
        holder.let {
            val id = item.id
            it.binding.imgFavorite.setImageFavorite(item.favorite)
            it.binding.txNumber.text = item.formattedNumber
            it.binding.name = formatTitle(item.name)
            it.binding.cardPokemon.colorCardView(item.types1)
            it.binding.imgPokemon.imageFromUrl(item.urlImg)
            holder.binding.cardPokemon.setOnClickListener {
                val transition = HomeFragmentDirections
                    .actionHomeFragmentToDatailsFragment(number = id)
                Navigation.findNavController(it)
                    .navigate(transition)
            }
        }
    }
    class ViewHolder(val binding: ItenPokemonBinding): RecyclerView.ViewHolder(binding.root)

    class PokemonUICallBack: DiffUtil.ItemCallback<PokemonUI>() {
        override fun areItemsTheSame(oldItem: PokemonUI, newItem: PokemonUI): Boolean {
            return oldItem.favorite == newItem.favorite
        }

        override fun areContentsTheSame(oldItem: PokemonUI, newItem: PokemonUI): Boolean {
            return oldItem == newItem
        }

    }

}







