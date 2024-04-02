package com.example.myapplication.ui.movie_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myapplication.ui.main_screen.MainScreenViewModel
import com.example.myapplication.util.UiEvent

@Composable
fun MovieScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: MainScreenViewModel = hiltViewModel()
){
    Column (
         modifier = Modifier
             .fillMaxSize()
             .padding(vertical = 40.dp)
             .padding(horizontal = 20.dp)
    ){
        Row {
            Image(
                painter = ,
                contentDescription = null
            )
        
            Column (
            ){
                Text(text = "Год выпуска: ${}")
                Text(text = "Рейтинг: ${}")
                Text(text = "Продолжительность: ${}")
                Text(text = "Возрастная категория: ${}")
            }
        }
        
        Row() {
            Column {
                Text(text = "//Название фильма")
                Text(text = "//Описание фильма")
            }
        }
        
    }
}
