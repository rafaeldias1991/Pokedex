package br.com.rafaeldias.apipokedex.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import br.com.rafaeldias.apipokedex.ui.PokemonUI

class MyDiffUtil(
    private val oldList: List<PokemonUI>,
    private val newList: List<PokemonUI>
): DiffUtil.Callback() {
    override fun getOldListSize()= oldList.size

    override fun getNewListSize()= newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].name == newList[newItemPosition].name
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].favorite == newList[newItemPosition].favorite
    }

}