package com.example.weatherapplication

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jlegacyai.weatherapplication.R
import kotlinx.coroutines.delay


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherApp()
        }
    }
}

class SharedPrefManager(context: Context) {
    private val sharedPref: SharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

    fun saveValue(key: String, value: String) {
        val editor = sharedPref.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getValue(key: String, defaultValue: String): String {
        return sharedPref.getString(key, defaultValue) ?: defaultValue
    }
}

@Composable
fun WeatherApp(){
    var sharedPrefManager: SharedPrefManager = SharedPrefManager(LocalContext.current)
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "homepage"){
        composable("weathermanager"){WeatherManager(navController)}
        composable("citytemperature"){LastScreen(navController)}
        composable("homepage"){HomePage(navController) }
    }
}

@Composable
fun HomePage(navController: NavController) {
    var sharedPrefManager: SharedPrefManager = SharedPrefManager(LocalContext.current)
    LaunchedEffect(Unit) {

        delay(3000)// Wait for 3 seconds
        navController.navigate("weathermanager")
    }

    //        val weatherInfo: List<String> = run {
//
//        val resourceId = resources.getIdentifier("weather_info_${cityName.lowercase()}", "array", context.packageName)
//
//        if (resourceId != 0) resources.getStringArray(resourceId).toList() else listOf("Info not available")
//
//}

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Weather Application Logo",
        )
        Text(text = "Sky Sight", fontSize= 25.sp)
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherManager(navController: NavController){
    var sharedPrefManager: SharedPrefManager = SharedPrefManager(LocalContext.current)
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Select city")
                }
            )
        },
        bottomBar = {
           BottomAppBar {
               Row(
                   modifier = Modifier
                       .fillMaxSize()
                       .height(40.dp)
                       .padding(10.dp) ,
                   horizontalArrangement = Arrangement.Start
               ){
                   Icon(Icons.Filled.Favorite, "icon")
               }
           }
        },
        content = {
            var inputCity by rememberSaveable { mutableStateOf("") }
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "logo",
                    modifier = Modifier.size(80.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.map),
                    contentDescription = "map",
                    modifier = Modifier
                        .height(100.dp)
                        .width(100.dp)
                )
                TextField(
                    value = inputCity,
                    onValueChange = { inputCity = it },
                    modifier = Modifier.padding(16.dp),
                    label = {
                        Text(text = "City")
                    }
                )
                Button(
                    onClick = {
                        sharedPrefManager.saveValue("name", inputCity)
                        navController.navigate("citytemperature")
                    },
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(text = "Show Weather")
                }
            }
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LastScreen(navController: NavController) {

    var sharedPrefManager: SharedPrefManager = SharedPrefManager(LocalContext.current)
    val cityName = sharedPrefManager.getValue("name", "") as String
    val context = LocalContext.current;
    val weatherInfo: List<String> = run {
        val resourceId = context.resources.getIdentifier("weather_info_${cityName.lowercase()}", "array", context.packageName)
        if (resourceId != 0) context.resources.getStringArray(resourceId).toList() else listOf("Info not available")

    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Weather Detail")
                }
            )
        },
        content = {
            if(weatherInfo[0] == ("Info not available")){
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .fillMaxWidth(1f),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "${cityName} Info not available")
                }
            }else{

                val weatherIconId = when(weatherInfo[3].lowercase()) {
                    "cloudy" -> R.drawable.cloudy_icon
                    "hot" -> R.drawable.hot_icon
                    "rainy" -> R.drawable.rainy_icon
                    "snow" -> R.drawable.snow_icon
                    "sunny" -> R.drawable.sunny_icon
                    else -> R.drawable.sunny_icon
                }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .fillMaxWidth(1f),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "City Name ${cityName}", fontSize = 25.sp)

                    Row(verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(0.dp, 12.dp)) {
                        Image(
                            painter = painterResource(R.drawable.temperature_icon),
                            contentDescription = "Temperature",
                            modifier = Modifier
                                .size(56.dp)
                                .padding(8.dp)
                        )
                        Column {
                            Text(text = "Temperature", fontSize = 16.sp)
                            Text(text = weatherInfo[1], fontSize = 12.sp)
                        }
                    }



                    Row(verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(0.dp, 12.dp)) {
                        Image(
                            painter = painterResource(R.drawable.temperature_icon),
                            contentDescription = "Temperature",
                            modifier = Modifier
                                .size(56.dp)
                                .padding(8.dp)
                        )
                        Column {
                            Text(text = "Humidity", fontSize = 16.sp)
                            Text(text = weatherInfo[2], fontSize = 12.sp)
                        }
                    }


                    Row(verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(0.dp, 12.dp)) {
                        Image(
                            painter =  painterResource(weatherIconId),
                            contentDescription = "Temperature",
                            modifier = Modifier
                                .size(56.dp)
                                .padding(8.dp)
                        )
                        Column {
                            Text(text = "Condition", fontSize = 16.sp)
                            Text(text = weatherInfo[3], fontSize = 12.sp)
                        }
                    }
                }
            }
        }
    )
}
