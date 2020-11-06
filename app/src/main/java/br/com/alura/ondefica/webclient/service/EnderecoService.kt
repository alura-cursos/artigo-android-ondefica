package br.com.alura.ondefica.webclient.service

import br.com.alura.ondefica.model.Endereco
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface EnderecoService {

    @GET("{cep}/json")
    suspend fun buscaEndereco(@Path("cep") cep: String): Response<Endereco>

}
