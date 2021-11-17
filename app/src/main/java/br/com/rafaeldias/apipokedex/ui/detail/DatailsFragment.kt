package br.com.rafaeldias.apipokedex.ui.detail

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import br.com.rafaeldias.apipokedex.R
import br.com.rafaeldias.apipokedex.databinding.DatailsFragmentBinding
import br.com.rafaeldias.apipokedex.ui.PokemonUI
import br.com.rafaeldias.apipokedex.ui.home.HomeViewModel
import br.com.rafaeldias.apipokedex.ui.imageFromUrl
import br.com.rafaeldias.apipokedex.utils.PokemonColor
import br.com.rafaeldias.apipokedex.utils.formatTitle
import br.com.rafaeldias.apipokedex.utils.setImageButtonFavorite
import org.koin.androidx.viewmodel.ext.android.viewModel


class DatailsFragment : Fragment() {

    val args: DatailsFragmentArgs by navArgs()
    private val viewModel: HomeViewModel by viewModel()
    private val binding: DatailsFragmentBinding by lazy {
        DatailsFragmentBinding.inflate(layoutInflater)
    }
    lateinit var pokemon : PokemonUI

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val id = args.number
        loadDetail(id)
        binding.navController = findNavController()
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnFavorite.setOnClickListener {
            Log.e("save",pokemon.favorite.toString())
            updateFavoritePokemon(pokemon)
        }
    }



    private fun loadDetail(id: Int){
        viewModel.pokemonLiveData.observe(viewLifecycleOwner, Observer { pokemonList ->
            pokemon = pokemonList.get(id-1)
            binding.btnFavorite.setImageButtonFavorite(pokemon.favorite)
            setDetailPokemonImage(pokemon)
            setDetailPokemonProgressBar(pokemon)
            pokeDetailColor(pokemon)
            setDetailPokemonType(pokemon)
        })
    }

    fun setDetailPokemonProgressBar(pokemon: PokemonUI) {
        binding.apply {
            progressBarHp.progress = pokemon.statsHp.toFloat()
            progressBarHp.labelText = pokemon.statsHp.toString().uppercase()

            progressBarAtac.progress = pokemon.statsAttack.toFloat()
            progressBarAtac.labelText = pokemon.statsAttack.toString().uppercase()

            progressBarDef.progress = pokemon.statsDefense.toFloat()
            progressBarDef.labelText = pokemon.statsDefense.toString().uppercase()

            progressBarSpeed.progress = pokemon.statsSpeed.toFloat()
            progressBarSpeed.labelText = pokemon.statsSpeed.toString().uppercase()

        }
    }

    fun pokeDetailColor(pokemon: PokemonUI) {
        val context = requireContext()
        val color = PokemonColor(context).getTypeColor(pokemon.types1)
        if (pokemon.types2 !=null) {
            val color2 = PokemonColor(context).getTypeColor(pokemon.types2)
            binding.tvType2.background.colorFilter
                PorterDuffColorFilter(color2, PorterDuff.Mode.SRC_ATOP)
        }
        binding.constraint.background.colorFilter =
            PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP)

        activity?.window?.statusBarColor =
            PokemonColor(context).getTypeColor(pokemon.types1)
        binding.tvType1.background.colorFilter =
            PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP)
    }

    private fun setDetailPokemonImage(pokemon: PokemonUI){
        binding.imgPokemonDetail.imageFromUrl(pokemon.urlImg)
    }

    private fun setDetailPokemonType(pokemon: PokemonUI) {
        binding.apply {
            txNameDatail.text = formatTitle(pokemon.name)
            index.text = pokemon.formattedNumber
            tvType1.text = pokemon.types1
            if (pokemon.types2 ==null) {
                tvType2.visibility = View.VISIBLE
                tvType2.text = pokemon.types2
            } else {
                tvType2.visibility = View.GONE
            }
        }
    }


    fun updateFavoritePokemon(pokemon: PokemonUI){
           if (pokemon.favorite == true){
               binding.btnFavorite.setImageResource(R.drawable.pokeball_open)
               viewModel.updatePokemonFavorite(pokemon.id,false)}
           else{
               binding.btnFavorite.setImageResource(R.drawable.pokeball_close)
               viewModel.updatePokemonFavorite(pokemon.id,true)}

    }

}