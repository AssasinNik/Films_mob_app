package com.example.myapplication.ui.movie_list_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.myapplication.data.movie_data.Movie

@Composable
fun MovieListScreenItem(
    movie: Movie,
    modifier: Modifier
){
    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(120.dp)
    ){
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(25f)
            ) {
                Image(
                    painter = rememberImagePainter(movie.posterURL),
                    contentDescription = "movie_poster",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                )
            }

            Spacer(modifier = Modifier
                .width(6.dp))

            Column(
                modifier = Modifier

                    .fillMaxHeight()
                    .weight(75f)
            ) {
                Text(
                    text = movie.title ?: "",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(text = "2018",
                    fontSize = 13.sp,
                    color = Color.Gray
                )
                Text(text = "боевик, комедия",
                    fontSize = 13.sp,
                    color = Color.Gray
                )
            }
        }
    }
}