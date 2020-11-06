package br.com.alura.ondefica.repository

import androidx.lifecycle.liveData
import br.com.alura.ondefica.webclient.service.EnderecoService
import java.net.ConnectException

sealed class Resultado<out R> {
    data class Sucesso<out T>(val dado: T?) : Resultado<T?>()
    data class Erro(val exception: Exception) : Resultado<Nothing>()
}

class EnderecoRepository(
    private val service: EnderecoService
) {

    fun buscaEndereco(cep: String) = liveData {
        try {
            val resposta = service.buscaEndereco(cep)
            if(resposta.isSuccessful){
                emit(Resultado.Sucesso(dado = resposta.body()))
            } else {
                emit(Resultado.Erro(exception = Exception("Falha ao buscar o endereco")))
            }
        } catch (e: ConnectException) {
            emit(Resultado.Erro(exception = Exception("Falha na comunicação com API")))
        }
        catch (e: Exception) {
            emit(Resultado.Erro(exception = e))
        }
    }

}
