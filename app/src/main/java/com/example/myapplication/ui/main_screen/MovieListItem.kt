package com.example.myapplication.ui.main_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.data.Movie

@Composable
fun MovieListItem(
/*    movie: Movie,
    onEvent: (MainScreenEvent) -> Unit,*/
    modifier: Modifier = Modifier
){
    Card (
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(15.dp)
    ){
        Box(modifier = modifier.height(200.dp)) {
            Image(painter = painterResource(id = R.drawable.text_movie_poster), contentDescription = "movie_poster")
        }
    }
}