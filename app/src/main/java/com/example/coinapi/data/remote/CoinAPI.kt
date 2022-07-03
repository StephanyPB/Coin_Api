package com.example.coinapi.data.remote

import com.example.coinapi.data.remote.dto.Coindto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface CoinAPI {
    @GET("/Coins")
    suspend fun getCoin(): List<Coindto>

     @POST("/Coins")
     suspend fun postCoins(@Body coindto: Coindto): Coindto

}