package br.com.rafaeldias.apipokedex

import android.app.Application
import br.com.rafaeldias.apipokedex.di.useCaseModule
import br.com.rafaeldias.apipokedex.di.usedao
import br.com.rafaeldias.apipokedex.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MyApp)
            modules(viewModelModule)
            modules(useCaseModule)
            modules(usedao)
        }
    }
}