package com.example.coinapi.ui.components.coins

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.coinapi.data.remote.dto.Coindto
import com.example.coinapi.model.CoinViewModel


@Composable
fun ConsultaCoinScreen(
    navHostController: NavHostController
    ,viewModel: CoinViewModel = hiltViewModel()
) {
    val ScaffoldState = rememberScaffoldState()
    Scaffold(
        topBar ={
            TopAppBar(title = { Text( "Consulta Crypto") })
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navHostController.navigate("RegistroCoinScreen")
                },
                backgroundColor = MaterialTheme.colors.error
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Coin")
            }
        },
        scaffoldState = ScaffoldState
    ){it

        val state = viewModel.state.value

        Column(modifier = Modifier.fillMaxSize()) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.coins) { coins ->
                    CoinItem(coin = coins, {})
                }
            }

            if (state.isLoading)
                CircularProgressIndicator()
        }
    }
}


@Composable
fun CoinItem(
    coin: Coindto,
    onClick : (Coindto) -> Unit
) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .clickable { onClick(coin) }
        .padding(16.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter(coin.imagenUrl),
            contentDescription = null,
            modifier = Modifier.size(50.dp, 100.dp),
        )
        Row(
            modifier = Modifier.fillMaxWidth()
                .height(30.dp).padding(2.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "  ${coin.descripcion}",
                color = Color.DarkGray,
                fontStyle = FontStyle.Italic,
                style = MaterialTheme.typography.body1,
            )
            Text(
                text = "$ ${coin.valor}",
                color = Color.Green,
                fontStyle = FontStyle.Italic,
                style = MaterialTheme.typography.body2,
            )
        }
    }
}