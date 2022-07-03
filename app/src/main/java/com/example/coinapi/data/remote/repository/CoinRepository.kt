package com.example.coinapi.data.remote.repository

import com.example.coinapi.data.remote.CoinAPI
import com.example.coinapi.data.remote.dto.Coindto
import com.example.coinapi.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

class CoinRepository @Inject constructor(
    private val coinAPI: CoinAPI
) {
    fun getCoin(): Flow<Resource<List<Coindto>>> = flow {
        try {
            emit(Resource.Loading()) //indicar que estamos cargando

            val coins = coinAPI.getCoin() //descarga las monedas de interneto

            emit(Resource.Success(coins)) //se cargo correctamente y pasarle las monedas
        } catch (e: HttpException) {
            //error general HTTP
            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            //debe verificar tu conexion a internet
            emit(Resource.Error(e.message ?: "verificar tu conexion a internet"))
        }
    }

    suspend fun postCoin(coindto: Coindto): Coindto {
        var result = Coindto(0,"",0.0)
        try {
             result =  coinAPI.postCoins(coindto)
        } catch (e: Exception) {
            System.out.println(e.message)
        }
        return result
    }
}