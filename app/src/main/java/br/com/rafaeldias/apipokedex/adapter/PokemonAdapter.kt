package br.com.rafaeldias.apipokedex.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.rafaeldias.apipokedex.databinding.ItenPokemonBinding
import br.com.rafaeldias.apipokedex.domain.Pokemon
import com.bumptech.glide.Glide
import java.util.*

class PokemonAdapter(

): RecyclerView.Adapter<PokemonAdapter.ViewHolder>(){

    val listPokemon: MutableList<Pokemon> = mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItenPokemonBinding
            .inflate(layoutInflater,parent, false
            )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val item =  listPokemon[position]
        holder.let {
            val number = item.url
                .replace("https://pokeapi.co/api/v2/pokemon/","")
                .replace("/","").toInt()
            it.binding.name = item.name.replaceFirstChar {
                if (it.isLowerCase())
                    it.titlecase(Locale.getDefault()
                    )else it.toString()
            }
            Glide.with(it.binding.root)
                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/" +
                        "pokemon/${number}.png")
                .into(it.binding.imgPokemon)

        }
    }

    override fun getItemCount() = listPokemon.size

    class ViewHolder(
        val binding: ItenPokemonBinding
        ): RecyclerView.ViewHolder(binding.root)


    fun addItemInList(items: List<Pokemon>) {
        listPokemon.addAll(items)
    }

}