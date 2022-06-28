package com.example.coinapi.data.remote.repository

import com.example.coinapi.data.remote.CoinAPI
import com.example.coinapi.data.remote.dto.Coindto
import com.example.coinapi.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CoinRepository @Inject constructor(
    private val coinAPI: CoinAPI
){
    fun getExchanges(): Flow<Resource<List<Coindto>>> = flow {
        try {
            emit(Resource.Loading()) //indicar que estamos cargando

            val coins = coinAPI.getExchange() //descarga las monedas de internet, se supone quedemora algo

            emit(Resource.Success(coins)) //indicar que se cargo correctamente y pasarle las monedas
        } catch (e: HttpException) {
            //error general HTTP
            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            //debe verificar tu conexion a internet
            emit(Resource.Error(e.message ?: "verificar tu conexion a internet"))
        }
    }
}