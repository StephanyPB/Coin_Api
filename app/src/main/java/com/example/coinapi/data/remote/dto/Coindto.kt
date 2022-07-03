package com.example.coinapi.data.remote.dto

data class Coindto (
    val monedaId: Int = 0,
    val descripcion: String,
    val valor: Double = 0.0,
    val imagenUrl: String? = ""
)