package br.com.alura.ondefica.model

data class Endereco(
    val logradouro: String,
    val bairro: String,
    val localidade: String,
    val cep: String,
    val uf: String
)