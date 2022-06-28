package com.example.coinapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.coinapi.data.remote.dto.Coindto
import com.example.coinapi.model.CoinViewModel
import com.example.coinapi.ui.theme.CoinApiTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoinApiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ConsultaCoinScreen()
                }
            }
        }
    }
}


@Composable
fun ConsultaCoinScreen(
    viewModel: CoinViewModel = hiltViewModel()
) {
    val ScaffoldState = rememberScaffoldState()

    Scaffold(
        topBar ={
            TopAppBar(title = { Text( "Consulta Coins") }) },
        scaffoldState = ScaffoldState
    ) {it

        val state = viewModel.state.value

        Column(modifier = Modifier.fillMaxSize()) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.exchange) { exchange ->
                    CoinItem(coin = exchange, {})
                }
            }

            if (state.isLoading)
                CircularProgressIndicator()
        }
    }
}


@Composable
fun CoinItem(
    coin:Coindto,
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
            modifier = Modifier.size(200.dp),
        )
        Row(
            modifier = Modifier.fillMaxWidth()
                .height(30.dp).padding(2.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "${coin.descripcion}",
                color = Color.Green,
                fontStyle = FontStyle.Italic,
                style = MaterialTheme.typography.body2,
            )
                Text(
                    text = "${coin.valor}",
                    color = Color.Green,
                    fontStyle = FontStyle.Italic,
                    style = MaterialTheme.typography.body2,
                )
                Image(
                    painter = rememberAsyncImagePainter(coin.imagenUrl),
                    contentDescription = null,
                    modifier = Modifier.size(200.dp),
                )

        }
    }
}