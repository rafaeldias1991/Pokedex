package br.com.rafaeldias.apipokedex.ui.adapter

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.rafaeldias.apipokedex.databinding.ItenPokemonBinding
import br.com.rafaeldias.apipokedex.ui.PokemonUI
import br.com.rafaeldias.apipokedex.ui.home.HomeFragmentDirections
import br.com.rafaeldias.apipokedex.utils.PokemonColor
import com.bumptech.glide.Glide
import java.util.*

class PokemonAdapter: ListAdapter<PokemonUI, PokemonAdapter.ViewHolder>(PokemonUICallBack()){
    private lateinit var binding: ItenPokemonBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = ItenPokemonBinding
            .inflate(layoutInflater,parent, false
            )
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val item =  getItem(position)
        holder.let {
            val number = item.order
            it.binding.name = item.name.replaceFirstChar {
                if (it.isLowerCase())
                    it.titlecase(Locale.getDefault()
                    )else it.toString()
            }
            Glide.with(it.binding.root)
                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/"+
                        "pokemon/other/official-artwork/${number}.png")
                .timeout(6000)
                .into(it.binding.imgPokemon)
            holder.binding.cardPokemon.setOnClickListener {
                val transition = HomeFragmentDirections
                    .actionHomeFragmentToDatailsFragment(number = number)
                Navigation.findNavController(it)
                    .navigate(transition)
            }
        }
    }

  //  override fun getItemCount() = listPokemon.size

    class ViewHolder(
        val binding: ItenPokemonBinding
        ): RecyclerView.ViewHolder(binding.root)

    class PokemonUICallBack: DiffUtil.ItemCallback<PokemonUI>() {
        override fun areItemsTheSame(oldItem: PokemonUI, newItem: PokemonUI): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: PokemonUI, newItem: PokemonUI): Boolean {
            return oldItem.name == newItem.name
        }

    }

   // fun addItemInList(items: List<PokemonUI>) {
   //     listPokemon.addAll(items)
  //  }
}