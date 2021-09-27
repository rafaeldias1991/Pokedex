package br.com.rafaeldias.apipokedex.di

import br.com.rafaeldias.apipokedex.adapter.PokemonAdapter
import br.com.rafaeldias.apipokedex.api.repository.ItemDaoRepository
import br.com.rafaeldias.apipokedex.ui.detail.DatailsViewModel
import br.com.rafaeldias.apipokedex.ui.home.HomeViewModel
import br.com.rafaeldias.apipokedex.usecase.ApplySearchFilter
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(get())}
    viewModel { DatailsViewModel(get())}


}
val useCaseModule = module {
    factory { PokemonAdapter() }
    factory <ApplySearchFilter>{ ApplySearchFilter() }

}

val usedao = module {
    single { ItemDaoRepository() }
}


