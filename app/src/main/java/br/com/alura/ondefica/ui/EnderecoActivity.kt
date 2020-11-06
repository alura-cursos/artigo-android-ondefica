package br.com.alura.ondefica.ui

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import br.com.alura.ondefica.databinding.ActivityEnderecoBinding
import br.com.alura.ondefica.model.Endereco
import br.com.alura.ondefica.repository.Resultado
import br.com.alura.ondefica.ui.viewmodel.EnderecoViewModel
import com.google.android.material.snackbar.Snackbar
import org.koin.android.viewmodel.ext.android.viewModel

class EnderecoActivity : AppCompatActivity() {

    private val viewModel: EnderecoViewModel by viewModel()
    private val binding by lazy {
        ActivityEnderecoBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraCampoCep()
    }

    private fun configuraCampoCep() {
        binding.botaoBuscar.setOnClickListener {
            val cep = binding.cep.text.toString()
            buscaEndereco(cep)
        }
    }

    private fun buscaEndereco(cep: String) {
        binding.progresso.show()
        viewModel.buscaEnderecoPelo(cep).observe(this) {
            binding.progresso.hide()
            val enderecoVisivel = it?.let { resultado ->
                when (resultado) {
                    is Resultado.Sucesso -> {
                        resultado.dado?.let { endereco ->
                            preencheEndereco(endereco)
                            true
                        } ?: false
                    }
                    is Resultado.Erro -> {
                        Snackbar.make(
                            binding.coordinatorLayout,
                            resultado.exception.message.toString(),
                            Snackbar.LENGTH_SHORT
                        ).show()
                        false
                    }
                }
            } ?: false

            binding.constraintLayoutInfoEndereco.visibility =
                if (enderecoVisivel) {
                    VISIBLE
                } else {
                    GONE
                }
        }
    }

    private fun preencheEndereco(endereco: Endereco) {
        binding.logradouro.text = endereco.logradouro
        binding.bairro.text = endereco.bairro
        binding.cidade.text = endereco.localidade
        binding.estado.text = endereco.uf
    }
}