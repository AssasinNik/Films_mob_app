package com.example.myapplication.ui.movie_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.example.myapplication.ui.main_screen.MainScreenViewModel
import com.example.myapplication.util.UiEvent

@Composable
fun MovieScreen(
    onPopBackStack: () -> Unit,
    viewModel: MovieScreenViewModel = hiltViewModel()
){
    Column (
         modifier = Modifier
             .fillMaxSize()
             .padding(vertical = 40.dp)
             .padding(horizontal = 15.dp)
    ){
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
                Text(text = "2019", fontSize = 15.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "Рейтинг:", fontSize = 15.sp, lineHeight = 16.sp)
                Text(text = "8.2", fontSize = 15.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "Продолжительность:", fontSize = 15.sp, lineHeight = 16.sp)
                Text(text = "2 часа 35 минут", fontSize = 15.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "Возрастная категория:", fontSize = 15.sp, lineHeight = 16.sp)
                Text(text = "17+", fontSize = 15.sp, fontWeight = FontWeight.Bold)
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
                Text(text = viewModel.description)
            }
        }
        
    }
}
