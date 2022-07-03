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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.coinapi.data.remote.dto.Coindto
import com.example.coinapi.model.CoinViewModel
import com.example.coinapi.ui.components.coins.CoinItem
import com.example.coinapi.ui.components.coins.ConsultaCoinScreen
import com.example.coinapi.ui.components.coins.RegistroCoinScreen
import com.example.coinapi.ui.theme.CoinApiTheme
import com.example.coinapi.util.Screen
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
                    MyApps()
                }
            }
        }
    }
}

@Composable
fun MyApps() {
    CoinApiTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            val navHostController = rememberNavController()

            NavHost(navController = navHostController, startDestination = Screen.ConsutaCoinScreen.route) {
                composable(route = Screen.ConsutaCoinScreen.route) {
                    ConsultaCoinScreen(navHostController = navHostController)
                }
                composable(route = Screen.RegistroCoinScreen.route){
                    RegistroCoinScreen(navHostController = navHostController)
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    CoinApiTheme {
        MyApps()
    }
}