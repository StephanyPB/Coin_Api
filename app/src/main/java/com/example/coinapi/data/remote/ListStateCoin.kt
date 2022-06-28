package com.example.coinapi.data.remote

import com.example.coinapi.data.remote.dto.Coindto

data class ListStateCoin(
    val isLoading: Boolean = false,
    val exchange: List<Coindto> = emptyList(),
    val error: String = ""
)