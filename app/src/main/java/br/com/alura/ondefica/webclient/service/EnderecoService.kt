package br.com.alura.ondefica.webclient.service

import br.com.alura.ondefica.model.Endereco
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface EnderecoService {

    @GET("{cep}/json")
    fun buscaEndereco(@Path("cep") cep: String): Call<Endereco>

}
