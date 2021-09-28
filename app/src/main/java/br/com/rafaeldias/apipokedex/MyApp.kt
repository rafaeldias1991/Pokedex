package br.com.rafaeldias.apipokedex

import android.app.Application
import br.com.rafaeldias.apipokedex.di.getAppModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MyApp)
            modules(getAppModules())
        }
    }
}