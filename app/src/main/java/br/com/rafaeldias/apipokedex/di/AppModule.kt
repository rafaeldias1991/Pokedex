package br.com.rafaeldias.apipokedex.di

import br.com.rafaeldias.apipokedex.data.repository.PokedexRepositoryImpl
import br.com.rafaeldias.apipokedex.data.repository.Repository
import br.com.rafaeldias.apipokedex.data.local.PokemonDataSource
import br.com.rafaeldias.apipokedex.data.local.PokemonDataSourceImp
import br.com.rafaeldias.apipokedex.data.remote.PokedexApi
import br.com.rafaeldias.apipokedex.data.remote.ServiceClient
import br.com.rafaeldias.apipokedex.ui.adapter.PokemonAdapter
import br.com.rafaeldias.apipokedex.ui.detail.DatailsViewModel
import br.com.rafaeldias.apipokedex.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val pokedexModule = module {
    single { ServiceClient.getServiceClient(apiClass = PokedexApi::class.java) }
    factory <PokemonDataSource>{ PokemonDataSourceImp(pokeDAO = get()) }
}

private val viewModelModule = module {
    viewModel { HomeViewModel( pokedexRepository = get())}
    viewModel { DatailsViewModel(pokedexRepository = get())}
}

private val adapterModule = module {
    factory { PokemonAdapter() }
}
private val repositoryModule = module {
    factory<Repository>{ PokedexRepositoryImpl(
        api = get(),
        pokemonDataSource = get()) }


}





fun getAppModules() = listOf(viewModelModule, adapterModule, pokedexModule,repositoryModule)


