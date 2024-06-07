package com.example.myapplication.ui.new_movie_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.example.myapplication.util.UiEvent

@Composable
fun NewMovieScreen(
    onPopBackStack: () -> Unit,
    viewModel: NewMovieScreenViewModel = hiltViewModel()
){
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.PopBackStack -> onPopBackStack()
                else -> Unit
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Image(
            painter = rememberImagePainter(viewModel.posterUrl),
            contentDescription = "background_image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .blur(radius = 10.dp)
        )
        Box(modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.radialGradient(
                    colors = listOf(
                        Color.Black,
                        Color.Transparent
                    ),
                    radius = 2500f,
                )
            )
        )
    }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 10.dp)
            .padding(horizontal = 15.dp)
    ){
        Row {
            Icon(
                modifier = Modifier
                    .clickable {
                        viewModel.onEvent(NewMovieScreenEvent.OnBackIconClick)
                    },
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back"
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier
                .weight(45f)
        ) {
            Card (
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier
                    .weight(55f)
            ){
                Box(modifier = Modifier
                    .height(300.dp)
                    .fillMaxWidth()
                ) {
                    Image(
                        painter = rememberImagePainter(viewModel.posterUrl),
                        contentDescription = "movie_poster",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }
            }
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(45f)
                    .padding(start = 15.dp, top = 5.dp)
            ){
                Text(text = "Год выпуска:", fontSize = 15.sp, lineHeight = 16.sp)
                Text(text = viewModel.releaseYear, fontSize = 15.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "Рейтинг:", fontSize = 15.sp, lineHeight = 16.sp)
                Text(text = viewModel.rating, fontSize = 15.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "Продолжительность:", fontSize = 15.sp, lineHeight = 16.sp)
                Text(text = "${viewModel.length} минут", fontSize = 15.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "Возрастная категория:", fontSize = 15.sp, lineHeight = 16.sp)
                Text(text = viewModel.ageLimit, fontSize = 15.sp, fontWeight = FontWeight.Bold)
            }
        }

        Row(
            modifier = Modifier
                .weight(55f)
                .padding(top = 20.dp)
        ){
            Column {
                Text(text = viewModel.title, fontSize = 25.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(3.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(Color.White)
                )
                Spacer(modifier = Modifier.height(14.dp))
                Text(
                    text = viewModel.description,
                    modifier = Modifier
                        .verticalScroll(rememberScrollState(), true)
                )
            }
        }
    }
}
