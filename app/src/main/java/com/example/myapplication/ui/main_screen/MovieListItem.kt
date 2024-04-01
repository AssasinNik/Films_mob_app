package com.example.myapplication.ui.main_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.myapplication.R
import com.example.myapplication.data.Movie

@OptIn(ExperimentalCoilApi::class)
@Composable
fun MovieListItem(
    movie: Movie,
    onEvent: (MainScreenEvent) -> Unit,
    modifier: Modifier = Modifier
){
    Card (
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(30.dp)
    ){
        Box(modifier = modifier
            .height(300.dp)
            .width(190.dp)
        ) {
            Image(
                painter = rememberImagePainter(movie.posterURL),
                contentDescription = "movie_poster",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
            )
        }
    }
}