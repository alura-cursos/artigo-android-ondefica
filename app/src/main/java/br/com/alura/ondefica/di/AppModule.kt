package br.com.alura.ondefica.di

import br.com.alura.ondefica.repository.EnderecoRepository
import br.com.alura.ondefica.ui.viewmodel.EnderecoViewModel
import br.com.alura.ondefica.webclient.service.EnderecoService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val URL_BASE = "https://viacep.com.br/ws/"

val retrofitModule = module {
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(URL_BASE)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    single<OkHttpClient> {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }
    single<EnderecoService> { get<Retrofit>().create(EnderecoService::class.java) }
}

val repositoryModule = module {
    single<EnderecoRepository> { EnderecoRepository(get()) }
}

val viewModelModule = module {
    viewModel<EnderecoViewModel> { EnderecoViewModel(get()) }
}

val appModules = listOf(
    retrofitModule, repositoryModule, viewModelModule
)