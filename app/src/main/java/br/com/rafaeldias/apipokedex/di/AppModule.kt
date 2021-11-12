package br.com.rafaeldias.apipokedex.di

<<<<<<< HEAD
import br.com.rafaeldias.apipokedex.adapter.PokemonAdapter
import br.com.rafaeldias.apipokedex.api.repository.ItemDaoRepository
=======
import br.com.rafaeldias.apipokedex.data.repository.PokedexRepositoryImpl
import br.com.rafaeldias.apipokedex.data.repository.PokedexRepository
import br.com.rafaeldias.apipokedex.data.local.PokemonDataSource
import br.com.rafaeldias.apipokedex.data.local.PokemonDataSourceImp
import br.com.rafaeldias.apipokedex.data.remote.PokedexApi
import br.com.rafaeldias.apipokedex.data.remote.ServiceClient
import br.com.rafaeldias.apipokedex.ui.adapter.PokemonAdapter
>>>>>>> master
import br.com.rafaeldias.apipokedex.ui.detail.DatailsViewModel
import br.com.rafaeldias.apipokedex.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

<<<<<<< HEAD
val viewModelModule = module {

    viewModel { HomeViewModel(get())}
    viewModel { DatailsViewModel(get())}


}
val useCaseModule = module {
    factory { PokemonAdapter() }
    factory <ApplySearchFilter>{ ApplySearchFilter() }

=======
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
>>>>>>> master
}
private val repositoryModule = module {
    factory<PokedexRepository>{ PokedexRepositoryImpl(
        api = get(),
        pokemonDataSource = get()) }


<<<<<<< HEAD
val usedao = module {
    single { ItemDaoRepository() }
}

=======
}





fun getAppModules() = listOf(viewModelModule, adapterModule, pokedexModule,repositoryModule)

>>>>>>> master

