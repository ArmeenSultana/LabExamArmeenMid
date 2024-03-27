//package com.example.weatherapplication

//import android.content.Context
//import androidx.navigation.NavController
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.rememberNavController
//import androidx.activity.compose.setContent
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.Button
//import androidx.compose.material3.TextField
//import androidx.compose.material3.Text
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.saveable.rememberSaveable
//
//import androidx.compose.runtime.setValue
//
//import android.os.Bundle
//import androidx.compose.foundation.Image
//import androidx.activity.ComponentActivity
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.fillMaxHeight
//import androidx.compose.material3.BottomAppBar
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.TopAppBar
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavHostController
//
//class CitySelection : ComponentActivity() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            WeatherLog()
//        }
//    }
//}

// Given Code

//LaunchedEffect(Unit) {
//
//    delay(3000) // Wait for 3 seconds
//
//    // code to be called after 3 seconds
//
//}
//
//
//
//Get Dynamic Data from Strings
//
//val weatherInfo: List<String> = run {
//
//    val resourceId = resources.getIdentifier("weather_info_${cityName.lowercase()}", "array", context.packageName)
//
//    if (resourceId != 0) resources.getStringArray(resourceId).toList() else listOf("Info not available")
//
//}



// INTENT CODE


//@Composable
//fun WeatherLog(){
//    val navController = rememberNavController()
//    NavHost(navController = navController, startDestination = "screenA") {
//        composable("screenA") { WeatherL(navController) }
////        composable("screenB") { Weather(navController)
//        }
//
//    }
//}
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun WeatherL(context: NavHostController) {
//    var value by rememberSaveable{mutableStateOf("")}
//
//
//    Scaffold(
//        topBar = { TopAppBar(title = { Text(text = "Select City") })},
//        bottomBar = { BottomAppBar {
//
//        }}
//    ) {
//            paddingValues ->
//        Column( horizontalAlignment = Alignment.CenterHorizontally,modifier = Modifier
//            .padding(paddingValues)
//            .fillMaxSize()) {
//
//            Image(
//                modifier = Modifier
//                    .fillMaxHeight(0.2f)
//                    .fillMaxWidth(0.3f)
//                    .padding(0.dp, 0.dp, 0.dp, 0.dp)
//                ,
//                painter = painterResource(id = R.drawable.logo),
//                contentDescription = "Sky Sight")
//
//            Image(
//                modifier = Modifier
//                    .fillMaxHeight(0.4f)
//                    .fillMaxWidth()
//                    .padding(0.dp, 0.dp, 0.dp, 0.dp)
//                ,
//                painter = painterResource(id = R.drawable.map),
//                contentDescription = "Map")
//            TextField(value = value ,modifier=Modifier.padding(0.dp, 0.dp, 0.dp, 10.dp), onValueChange = {
//            value = it
//        })
//
//        Button(onClick = {
//            navController.navigate("Weather", navigatorExtras = value) }
//
//        ) {
//            Text("Show Weather")
//        }
//
//    }
//
//    }}
//

// Sample

//
//LazyVerticalGridComposeTheme {
//    val state = rememberLazyListState(
//        initialFirstVisibleItemIndex = 99
//    )
//    LazyVerticalGrid(
//        cells = GridCells.Fixed(5),
//        state = state,
//        content = {
//            items(100) { i ->
//                Box(
//                    modifier = Modifier
//                        .padding(8.dp)
//                        .aspectRatio(1f)
//                        .clip(RoundedCornerShape(5.dp))
//                        .background(Color.Green),
//                    contentAlignment = Alignment.Center
//                ) {
//                    Text(text = "Item $i")
//                }
//            }
//        }
//    )
//}



// Camera Access


//fun UseCameraAndGetPicture(modifier: Modifier= Modifier,
//                           context: Context = LocalContext.current){
//    Column {
//
//        val requestPermissionLauncher = rememberLauncherForActivityResult(
//            ActivityResultContracts.RequestPermission()){
//                isGranted -> if(isGranted){
//            Toast.makeText(context, "Permission Granted", Toast.LENGTH_SHORT).show()
//        } else{
//            Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
//        }
//        }
//
//        val cameraLauncher = rememberLauncherForActivityResult(
//            ActivityResultContracts.TakePicture())
//        {
//            if(it){
//
//            }
//            else{
//
//            }
//        }
//
//        Button(onClick = {
//            if (ContextCompat.checkSelfPermission(context,
//                    Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
//                cameraLauncher.launch(Uri.EMPTY)
//            }
//            else {
//                requestPermissionLauncher.launch(Manifest.permission.CAMERA)
//            }
//        }) {
//            Text(text = "Open Camera")
//
//        }
//    }
//
//}


// Error

//package com.example.errorsolving
//
//import android.annotation.SuppressLint
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Surface
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.tooling.preview.Preview
//import com.example.errorsolving.ui.theme.ErrorSolvingTheme
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.material.*
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Add
//import androidx.compose.material3.BottomAppBar
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.FloatingActionButton
//import androidx.compose.material3.Icon
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.TopAppBar
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavController
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.rememberNavController
//
//
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            ErrorSolvingTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    MyApp4()
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun MyApp4(){
//    val navController = rememberNavController()
//    NavHost(navController = navController,
//        startDestination = "home" ){
//        composable(route = "home"){
//            MainScreen(navController = navController)}
//
//        composable(route = "profile"){
//            Greeting("Meharwan")
//        }
//    }
//}
//
//@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun MainScreen(navController: NavController) {
//    Scaffold(
//        topBar = { TopAppBar(title = { Text("Main Screen") }) },
//        floatingActionButton = {  FloatingActionButton(
//            onClick = {
//                navController.navigate("profile") // Navigate to new screen
//            },
//
//            ) {
//            Icon(
//                imageVector = Icons.Default.Add,
//                contentDescription = "Add"
//            )
//        }
//
//        },
//        bottomBar = { BottomBar(navController) },
//        content = { Content() }
//    )
//}
//
//@Composable
//fun BottomBar(navController: NavController) {
//
//    BottomAppBar() {
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(16.dp),
//            horizontalArrangement = Arrangement.SpaceBetween,
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Text("Bottom Bar")
//        }
//    }
//}
//
//@Composable
//fun Content() {
//    val dummyData = List(20) { "Item $it" }
//
//    LazyColumn(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp)
//    ) {
//        items(dummyData) { item ->
//            Text(text = item, modifier = Modifier.padding(8.dp))
//        }
//    }
//}
//
//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    ErrorSolvingTheme {
//        Greeting("Android")
//    }
//}
