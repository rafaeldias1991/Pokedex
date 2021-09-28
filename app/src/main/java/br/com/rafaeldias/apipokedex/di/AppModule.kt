package br.com.rafaeldias.apipokedex.di

import br.com.rafaeldias.apipokedex.adapter.PokemonAdapter
import br.com.rafaeldias.apipokedex.api.PokedexApi
import br.com.rafaeldias.apipokedex.api.ServiceClient
import br.com.rafaeldias.apipokedex.api.repository.ItemDaoRepository
import br.com.rafaeldias.apipokedex.api.repository.PokedexRepository
import br.com.rafaeldias.apipokedex.ui.detail.DatailsViewModel
import br.com.rafaeldias.apipokedex.ui.home.HomeViewModel
import br.com.rafaeldias.apipokedex.usecase.ApplySearchFilter
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val pokedexModule = module {
    single { ServiceClient.getServiceClient(apiClass = PokedexApi::class.java) }

    factory { PokedexRepository(api = get()) }
}

val viewModelModule = module {

    viewModel { HomeViewModel(idaov = get(), pokedexRepository = get())}
    viewModel { DatailsViewModel(get())}


}
private val useCaseModule = module {
    factory { PokemonAdapter() }
    factory { ApplySearchFilter() }

}

private val useDao = module {
    single { ItemDaoRepository() }
}

fun getAppModules() = listOf(viewModelModule, useCaseModule, useDao, pokedexModule)


