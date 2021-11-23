package br.com.rafaeldias.apipokedex.di

import br.com.rafaeldias.apipokedex.data.repository.PokedexRepositoryImpl
import br.com.rafaeldias.apipokedex.data.repository.PokedexRepository
import br.com.rafaeldias.apipokedex.data.local.PokemonDataSource
import br.com.rafaeldias.apipokedex.data.local.PokemonDataSourceImp
import br.com.rafaeldias.apipokedex.data.remote.PokedexApi
import br.com.rafaeldias.apipokedex.data.remote.ServiceClient
import br.com.rafaeldias.apipokedex.ui.adapter.ApplySearchFilterName
import br.com.rafaeldias.apipokedex.ui.adapter.PokemonAdapter
import br.com.rafaeldias.apipokedex.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val pokedexModule = module {
    single { ServiceClient.getServiceClient(apiClass = PokedexApi::class.java) }
    factory <PokemonDataSource>{ PokemonDataSourceImp(pokeDAO = get()) }
}

private val viewModelModule = module {
    viewModel { HomeViewModel( pokedexRepository = get(),applySearchFilterName = get())}

}

private val adapterModule = module {
    factory { PokemonAdapter() }
    factory { ApplySearchFilterName() }
}
private val repositoryModule = module {
    factory<PokedexRepository>{ PokedexRepositoryImpl(
        api = get(),
        pokemonDataSource = get()) }
}

fun getAppModules() = listOf(viewModelModule, adapterModule, pokedexModule,repositoryModule)

