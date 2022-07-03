package com.example.coinapi.util

sealed class Screen (val route: String){
    object RegistroCoinScreen: Screen("RegistroCoinScreen")

    object ConsutaCoinScreen: Screen("ConsultaCoinScreen")
}