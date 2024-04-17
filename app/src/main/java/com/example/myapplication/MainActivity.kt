package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.myapplication.ui.login_screen.LoginScreen
import com.example.myapplication.ui.main_screen.MainScreen
import com.example.myapplication.ui.mood_test_pager.MoodTestPager
import com.example.myapplication.ui.movie_list_screen.MovieListScreen
import com.example.myapplication.ui.movie_screen.MovieScreen
import com.example.myapplication.ui.reusable_composeables.BottomNavBar
import com.example.myapplication.ui.reusable_composeables.BottomNavGraph
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.ui.user_screen.UserScreen
import com.example.myapplication.util.Routes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface (
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ){
                    MoodTestPager()
                    //LoginScreen()
                    //UserScreen()
                    //RegisterScreen()
                    /*val navController = rememberNavController()
                    Scaffold(
                        bottomBar = {
                            BottomNavBar(navController = navController)
                        }
                    ){
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

                            composable(Routes.USER_SCREEN){
                                UserScreen(onPopBackStack = {
                                    navController.popBackStack()
                                })
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
                    }*/
                }
            }
        }
    }
}
