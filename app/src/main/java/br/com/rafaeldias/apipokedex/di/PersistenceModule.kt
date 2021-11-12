package br.com.rafaeldias.apipokedex.di

import androidx.room.Room
import br.com.rafaeldias.apipokedex.data.local.AppDatabase
import br.com.rafaeldias.apipokedex.data.local.PokeDAO
import br.com.rafaeldias.apipokedex.utils.AppConstants.DATABASE_NAME
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val persistenceModule = module {

    single {
        Room.databaseBuilder(
            androidApplication(),
            AppDatabase::class.java,
            DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    fun providePokeDao(database: AppDatabase): PokeDAO {
        return database.pokeDAO
    }
    single { providePokeDao(get()) }



}