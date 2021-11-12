package br.com.rafaeldias.apipokedex.ui.detail

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import br.com.rafaeldias.apipokedex.databinding.DatailsFragmentBinding
import br.com.rafaeldias.apipokedex.ui.PokemonUI
import br.com.rafaeldias.apipokedex.ui.home.HomeViewModel
import br.com.rafaeldias.apipokedex.ui.imageFromUrl
import br.com.rafaeldias.apipokedex.utils.PokemonColor
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*


class DatailsFragment : Fragment() {

    val args: DatailsFragmentArgs by navArgs()
    private val viewModel: HomeViewModel by viewModel()
    private val binding: DatailsFragmentBinding by lazy {
        DatailsFragmentBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val number = args.number
        loadDetail(number)
        binding.navController = findNavController()
        return binding.root
    }

    private fun loadDetail(number: Int){
        viewModel.pokemonLiveData.observe(viewLifecycleOwner,){pokemonList ->
            setDetailPokemonImage(pokemonList.get(number))
            setDetailPokemonProgressBar(pokemonList.get(number))
            pokeDetailColor(pokemonList.get(number))
            setDetailPokemonType(pokemonList.get(number))
        }
    }

    fun setDetailPokemonProgressBar(it: PokemonUI) {
        binding.apply {
            progressBarHp.progress = it.statsHp.toFloat()
            progressBarHp.labelText = it.statsHp.toString().uppercase()

            progressBarAtac.progress = it.statsAttack.toFloat()
            progressBarAtac.labelText = it.statsAttack.toString().uppercase()

            progressBarDef.progress = it.statsDefense.toFloat()
            progressBarDef.labelText = it.statsDefense.toString().uppercase()

            progressBarSpeed.progress = it.statsSpeed.toFloat()
            progressBarSpeed.labelText = it.statsSpeed.toString().uppercase()

        }
    }

    fun pokeDetailColor(it: PokemonUI) {
        val context = requireContext()
        val color = PokemonColor(context).getTypeColor(it.types1)
        if (it.types2 !=null) {
            val color2 = PokemonColor(context).getTypeColor(it.types2)
            binding.tvType2.background.colorFilter
                PorterDuffColorFilter(color2, PorterDuff.Mode.SRC_ATOP)
        }
        binding.constraint.background.colorFilter =
            PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP)

        activity?.window?.statusBarColor =
            PokemonColor(context).getTypeColor(it.types1)
        binding.tvType1.background.colorFilter =
            PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP)
    }

    private fun setDetailPokemonImage(it: PokemonUI){
        binding.imgPokemonDetail.imageFromUrl(it.urlImg)
    }

    private fun setDetailPokemonType(it: PokemonUI) {
        binding.apply {
            txNameDatail.text = it.name.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
            }
            index.text = "Nº" + it.order
            tvType1.text = it.types1
            if (it.types2 ==null) {
                tvType2.visibility = View.VISIBLE
                tvType2.text = it.types2
            } else {
                tvType2.visibility = View.GONE
            }
        }
    }
}