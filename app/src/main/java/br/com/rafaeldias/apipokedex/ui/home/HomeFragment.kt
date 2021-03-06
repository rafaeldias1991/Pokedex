package br.com.rafaeldias.apipokedex.ui.home

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import br.com.rafaeldias.apipokedex.R
import br.com.rafaeldias.apipokedex.ui.adapter.PokemonAdapter
import br.com.rafaeldias.apipokedex.databinding.HomeFragmentBinding
import br.com.rafaeldias.apipokedex.ui.PokemonUI
import br.com.rafaeldias.apipokedex.ui.viewmodel.SharedViewModel
import br.com.rafaeldias.apipokedex.utils.PokemonColor
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class HomeFragment : Fragment() {

    private val binding: HomeFragmentBinding by lazy {
        HomeFragmentBinding.inflate(layoutInflater)
    }

    private val viewModel: SharedViewModel by sharedViewModel()
    private val pokemonAdapter: PokemonAdapter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.rvListPokemon.layoutManager = GridLayoutManager(requireContext(), 2)
        (activity as AppCompatActivity).setSupportActionBar(binding.myToolbar)
        loadItems()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.window?.statusBarColor = PokemonColor(view.context).getTypeColor("action_bar")
        binding.adapter = pokemonAdapter
        binding.rvListPokemon.adapter
    }

    private fun loadItems(){
        viewModel.filteredListPokemon.observe(viewLifecycleOwner,Observer { pokemonList ->
            initSearchBar()
            pokemonAdapter.swap(pokemonList)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
         super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_searchbar, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
                R.id.all_pokemons -> {
                    viewModel.filteredListPokemon.observe(viewLifecycleOwner, Observer {
                        pokemonAdapter.swap(it)
                    })
                true
            }
                R.id.favorites -> {
                    viewModel.filteredListPokemon.observe(viewLifecycleOwner, Observer {
                        pokemonAdapter.swap(mapFavorite(it))
                    })
                true
            }
                else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initSearchBar() {
        binding.homeSearchView.setOnQueryTextListener(object : android.widget.SearchView.OnQueryTextListener,
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    viewModel.setSearchQuery(it)
                    return true
                }
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    viewModel.setSearchQuery(it)
                    return true
                }
                return false
            }
        })
    }

    fun mapFavorite(list: List<PokemonUI>): List<PokemonUI>{
        val mapFavorite = list.filter { it.favorite == true}
        return mapFavorite
    }
}
