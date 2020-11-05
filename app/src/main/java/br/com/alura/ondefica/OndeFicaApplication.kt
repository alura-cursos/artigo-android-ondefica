package br.com.alura.ondefica

import android.app.Application
import br.com.alura.ondefica.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class OndeFicaApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@OndeFicaApplication)
            modules(appModules)
        }
    }

}