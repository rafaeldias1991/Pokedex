package br.com.rafaeldias.apipokedex.ui.detail

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import br.com.rafaeldias.apipokedex.databinding.DatailsFragmentBinding
import br.com.rafaeldias.apipokedex.domain.Pokemon
import br.com.rafaeldias.apipokedex.ui.home.HomeViewModel
import br.com.rafaeldias.apipokedex.utils.PokemonColor
import com.bumptech.glide.Glide
import com.skydoves.progressview.progressView
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
        binding.navController = findNavController()
        viewModel.loadPokemonDetail(number)
        observerPokeDetail()
        return binding.root
    }

    fun setDetailPokemon(it: Pokemon) {
        binding.apply {
            Glide.with(root)
                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${it.id}.png")
                .timeout(2000)
                .into(imgPokemonDetail)
            txNameDatail.text = it.name.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
            }
            index.text = "Nº" + it.order
            tvType1.text = it.types[0].type.name
            if (it.types.size > 1) {
                tvType2.visibility = View.VISIBLE
                tvType2.text = it.types[1].type.name
            } else {
                tvType2.visibility = View.GONE
            }
            progressBarHp.progress = it.stats[0].base_stat.toFloat()
            progressBarHp.labelText = it.stats[0].base_stat.toString().uppercase()

            progressBarAtac.progress = it.stats[1].base_stat.toFloat()
            progressBarAtac.labelText = it.stats[1].base_stat.toString().uppercase()

            progressBarDef.progress = it.stats[2].base_stat.toFloat()
            progressBarDef.labelText = it.stats[2].base_stat.toString().uppercase()

            progressBarSpeed.progress = it.stats[3].base_stat.toFloat()
            progressBarSpeed.labelText = it.stats[3].base_stat.toString().uppercase()

        }
    }

    fun observerPokeDetail() {
        viewModel.pokemonDataDetail.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            val context = requireContext()
            val color = PokemonColor(context).getTypeColor(it.types[0].type.name)
            if (it.types.size > 1) {
                val color2 = PokemonColor(context).getTypeColor(it.types[1].type.name)
                binding.tvType2.background.colorFilter =
                    PorterDuffColorFilter(color2, PorterDuff.Mode.SRC_ATOP)
            }
            binding.constraint.background.colorFilter =
                PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP)

            activity?.window?.statusBarColor =
                PokemonColor(context).getTypeColor(it.types[0].type.name)
            binding.tvType1.background.colorFilter =
                PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP)

            if (it != null) {
                setDetailPokemon(it)
            } else {
                Toast.makeText(requireContext(), "Erro ao carregar", Toast.LENGTH_SHORT).show()
            }
        })
    }



}