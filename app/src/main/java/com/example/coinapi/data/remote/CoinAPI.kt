package com.example.coinapi.data.remote

import com.example.coinapi.data.remote.dto.Coindto
import retrofit2.http.GET

interface CoinAPI {
    @GET("/Coins")
    suspend fun getExchange(): List<Coindto>

}