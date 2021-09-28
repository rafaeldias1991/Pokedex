package br.com.rafaeldias.apipokedex.ui.home

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import br.com.rafaeldias.apipokedex.R
import br.com.rafaeldias.apipokedex.adapter.PokemonAdapter
import br.com.rafaeldias.apipokedex.databinding.HomeFragmentBinding
import br.com.rafaeldias.apipokedex.utils.State
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(), SearchView.OnQueryTextListener {

    private val binding: HomeFragmentBinding by lazy {
        HomeFragmentBinding.inflate(layoutInflater)
    }

    private val viewModel: HomeViewModel by viewModel()
    private val pokemonAdapter: PokemonAdapter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.searchScrean = this
        binding.rvListPokemon.layoutManager = GridLayoutManager(requireContext(), 2)

        (activity as AppCompatActivity).setSupportActionBar(binding.myToolbar)

        loadItems()
/*        viewModel.pokemonData.observe(viewLifecycleOwner, {
            if (it !=null){
                binding.adapter = pokemonAdapter
                pokemonAdapter.addItemInList(it)
            }else{
                Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
            }
        })
        */
        return binding.root
    }

    private fun loadItems() {
        viewModel.fetchPokemonsList().observe(viewLifecycleOwner, { state ->
            when (state) {
                is State.LoadingState -> {
                }
                is State.DataState -> {
                    state.data?.let { list ->
                        binding.adapter = pokemonAdapter
                        pokemonAdapter.addItemInList(list)
                    }
                }
                is State.ErrorState -> {
                    Toast.makeText(requireContext(), state.exception.localizedMessage, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_searchbar, menu)
        val item = menu.findItem(R.id.search)
        val searchView = item.actionView as SearchView
        searchView.setOnQueryTextListener(this)

    }

    override fun onQueryTextSubmit(query: String): Boolean {
        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {
        return true
    }

    override fun onResume() {
        super.onResume()
        //viewModel.loadItems()
    }
}