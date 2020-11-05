package br.com.alura.ondefica.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.alura.ondefica.model.Endereco
import br.com.alura.ondefica.webclient.service.EnderecoService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EnderecoRepository(
    private val service: EnderecoService
) {

    fun buscaEndereco(cep: String): LiveData<Endereco?> {
        val liveData = MutableLiveData<Endereco?>()
        service.buscaEndereco(cep).enqueue(object : Callback<Endereco?> {

            override fun onResponse(call: Call<Endereco?>, response: Response<Endereco?>) {
                liveData.postValue(response.body())
            }

            override fun onFailure(call: Call<Endereco?>, t: Throwable) {
                Log.e("EnderecoRepository", "onFailure: falha ao buscar o endereço", t)
                liveData.postValue(null)
            }

        })
        return liveData
    }

}
