package com.example.hw2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = "WelcomeScreen"
            ) {
                composable("WelcomeScreen") {
                    WelcomeScreen(navController)
                }
                composable("SecondScreen") {
                    SecondScreen(navController)
                }
            }
        }
    }
}

@Composable
fun WelcomeScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Welcome",
            style = TextStyle(fontSize = 40.sp, color = Color.Black)
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                navController.navigate("SecondScreen") // Navigate to the second screen
            }
        ) {
            Text("Continue")
        }
    }
}

@Composable
fun SecondScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Button(
            onClick = {
                navController.navigate("WelcomeScreen") // Navigate to the first screen
            }
        ) {
            Text("Back")
        }
    }

    val cities = listOf(
        CityWithDescription("Yerevan", "Armenia's capital. It's currently 2,804 years old.", R.drawable.yerevan),
        CityWithDescription("Washington DC", "U.S.'s capital, where the White House is located.", R.drawable.washington),
        CityWithDescription("Madrid", "Spain's capital. Hosts one of the most popular football teams: Real Madrid.", R.drawable.madrid),
        CityWithDescription("London", "UK's capital. Has its football team, Big Ben, and used to have the infamous Queen", R.drawable.london)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Cities",
            style = TextStyle(fontSize = 40.sp, color = Color.Black)
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn() {
            items(cities) { city ->
                Cities(city)
            }
        }
        Button(
            onClick = {
                navController.navigate("WelcomeScreen")
            }
        ) {
            Text(text = "Back")
        }
    }
}

@Composable
fun Cities(cityWithDescription: CityWithDescription) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = cityWithDescription.imageResource),
            contentDescription = "Image of the city",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentScale = ContentScale.Crop
        )

        Text(
            text = cityWithDescription.name,
            style = TextStyle(fontSize = 26.sp, color = Color.Black)
        )
        Text(
            text = cityWithDescription.description,
            style = TextStyle(fontSize = 18.sp, color = Color.Gray)
        )
        Divider(color = Color.Gray, thickness = 1.dp)
    }
}

@Preview
@Composable
fun WelcomeScreenPreview() {
    val navController = rememberNavController()
    WelcomeScreen(navController)
}

@Preview
@Composable
fun SecondScreenPreview() {
    val navController = rememberNavController()
    SecondScreen(navController)
}

data class CityWithDescription(val name: String, val description: String, val imageResource: Int)
