package com.example.myapplication.ui.reusable_composeables

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplication.ui.main_screen.MainScreen
import com.example.myapplication.ui.movie_list_screen.MovieListScreen
import com.example.myapplication.util.Routes

@Composable
fun BottomNavGraph(navController: NavHostController){
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
    }
}