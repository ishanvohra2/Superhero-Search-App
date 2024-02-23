package com.ishanvohra2.superheroqueryapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ishanvohra2.superheroqueryapp.ui.components.HomeComponent
import com.ishanvohra2.superheroqueryapp.ui.components.ProfileComponent
import com.ishanvohra2.superheroqueryapp.ui.theme.SuperheroQueryAppTheme
import com.ishanvohra2.superheroqueryapp.viewModels.MainViewModel

class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    private val viewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SuperheroQueryAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "home"){
                        composable("home"){
                            HomeComponent{
                                viewModel.superhero = it
                                navController.navigate("profile")
                            }.HomePage()
                        }
                        composable("profile"){
                            viewModel.superhero?.let { s ->
                                ProfileComponent{
                                    navController.popBackStack()
                                }.Profile(superhero = s)
                            }
                        }
                    }
                }
            }
        }
    }
}