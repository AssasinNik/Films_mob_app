package com.example.myapplication.ui.main_screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myapplication.util.UiEvent

@Preview(showBackground = true)
@Composable
fun MainScreen(
/*    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: MainScreenViewModel = hiltViewModel()*/
){
    LazyRow (
        modifier = Modifier.fillMaxSize()
    ){
        items(count = 10){
            MovieListItem()
        }
    }
}