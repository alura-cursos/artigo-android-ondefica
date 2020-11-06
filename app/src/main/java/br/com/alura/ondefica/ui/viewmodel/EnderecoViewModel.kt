package br.com.alura.ondefica.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import br.com.alura.ondefica.model.Endereco
import br.com.alura.ondefica.repository.EnderecoRepository
import br.com.alura.ondefica.repository.Resultado

class EnderecoViewModel(private val repository: EnderecoRepository) : ViewModel() {

    fun buscaEnderecoPelo(cep: String): LiveData<Resultado<Endereco?>> =
        repository.buscaEndereco(cep)

}