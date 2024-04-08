package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapplication.ui.main_screen.MainScreen
import com.example.myapplication.ui.movie_list_screen.MovieListScreen
import com.example.myapplication.ui.movie_screen.MovieScreen
import com.example.myapplication.ui.register_screen.RegisterScreen
import com.example.myapplication.ui.reusable_composeables.BottomNavBar
import com.example.myapplication.ui.reusable_composeables.BottomNavBarItem
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.util.Routes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface (
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ){
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Routes.MAIN_SCREEN
                    ){
                        composable(Routes.MAIN_SCREEN){

                            MainScreen(onNavigate = {
                                navController.navigate(it.route)
                            })
                        }

                        composable(Routes.MOVIE_LIST_SCREEN){
                            MovieListScreen(
                                onNavigate = {
                                    navController.navigate(it.route)
                                },
                                onPopBackStack = {
                                    navController.popBackStack()
                                }
                            )
                        }

                        composable(
                            route = Routes.MOVIE_SCREEN + "?movieId={movieId}",
                            arguments = listOf(
                                navArgument(name = "movieId"){
                                    type = NavType.IntType
                                    defaultValue = -1
                                }
                            )
                        ) {
                            MovieScreen(onPopBackStack = {
                                navController.popBackStack()
                            })
                        }
                    }
                }
            }
        }
    }
}
