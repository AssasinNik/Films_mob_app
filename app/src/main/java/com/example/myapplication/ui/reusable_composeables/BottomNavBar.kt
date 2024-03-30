package com.example.myapplication.ui.reusable_composeables

import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavBarItem(
    val selectedIcon: ImageVector,
)

@Composable
fun BottomNavBar(){
    Scaffold(
        bottomBar = {
            NavigationBar {
            }
        }
    ){

    }
}