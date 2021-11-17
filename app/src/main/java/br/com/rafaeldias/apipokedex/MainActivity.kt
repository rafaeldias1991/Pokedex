package br.com.rafaeldias.apipokedex

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.rafaeldias.apipokedex.data.remote.PokemonResultApi.Home
import br.com.rafaeldias.apipokedex.databinding.ActivityMainBinding
import br.com.rafaeldias.apipokedex.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
    private val HomeViewModel by viewModel<HomeViewModel>()

}