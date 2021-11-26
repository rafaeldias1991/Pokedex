package br.com.rafaeldias.apipokedex.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.rafaeldias.apipokedex.databinding.ItenPokemonBinding
import br.com.rafaeldias.apipokedex.ui.PokemonUI
import br.com.rafaeldias.apipokedex.ui.utils.colorCardView
import br.com.rafaeldias.apipokedex.ui.home.HomeFragmentDirections
import br.com.rafaeldias.apipokedex.ui.imageFromUrl
import br.com.rafaeldias.apipokedex.utils.formatTitle
import br.com.rafaeldias.apipokedex.utils.setImageFavorite

class PokemonAdapter: RecyclerView.Adapter<PokemonAdapter.ViewHolder>(){
    lateinit var binding: ItenPokemonBinding

    private val oldPokemon = mutableListOf<PokemonUI>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = ItenPokemonBinding
            .inflate(layoutInflater,parent, false
            )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = oldPokemon[position]
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

    override fun getItemCount(): Int {
        return oldPokemon.size
    }

    fun swap(newPokemon: List<PokemonUI>) {
        val diffCallback = MyDiffUtil(this.oldPokemon, newPokemon)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.oldPokemon.clear()
        this.oldPokemon.addAll(newPokemon)
        diffResult.dispatchUpdatesTo(this)
    }


}







