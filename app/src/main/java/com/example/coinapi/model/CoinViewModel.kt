package com.example.coinapi.model

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coinapi.data.remote.ListStateCoin
import com.example.coinapi.data.remote.repository.CoinRepository
import com.example.coinapi.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class CoinViewModel @Inject constructor(
    private val coinRepository: CoinRepository
) : ViewModel(){

    private var _state = mutableStateOf(ListStateCoin())
    val state: State<ListStateCoin> = _state

    init {
        coinRepository.getExchanges().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = ListStateCoin(isLoading = true)
                }

                is Resource.Success -> {
                    _state.value = ListStateCoin(exchange = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = ListStateCoin(error = result.message ?: "Error desconocido")
                }
            }
        }.launchIn(viewModelScope)
    }
}