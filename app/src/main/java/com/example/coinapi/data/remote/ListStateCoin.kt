package com.example.coinapi.data.remote

import com.example.coinapi.data.remote.dto.Coindto

data class ListStateCoin(
    val isLoading: Boolean = false,
    val coins: List<Coindto> = emptyList(),
    val error: String = ""
)