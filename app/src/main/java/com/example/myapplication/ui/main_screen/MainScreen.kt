package com.example.myapplication.ui.main_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.ui.reusable_composeables.RoundImage

@Composable
fun MainScreen(
    /*onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: MainScreenViewModel = hiltViewModel()*/
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 40.dp)
    ){
        Greeting(
            userName = "Гигачадыч",
            userPhoto = painterResource(id = R.drawable.test_user_picture)
        )

        MovieForMood()

        HorizontalListOfNewMovies()
    }
}

@Composable
fun HorizontalListOfNewMovies(
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 20.dp),
            text = "Новинки",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(30.dp))

        LazyRow(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(count = 10) {
                MovieListItem()
            }
        }
    }
}

@Composable
fun MovieForMood(
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
            .padding(horizontal = 20.dp)
    ) {
        Text(
            text = "Фильм по настроению",
            fontSize = 15.sp
        )
        Spacer(modifier = Modifier.height(25.dp))

        Button(
            colors = ButtonDefaults.buttonColors(Color.Magenta),
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    text = "Подобрать фильм",
                    color = Color.White
                )
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = null,
                    tint = Color.White,
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(30.dp))
}

@Composable
fun Greeting(
    modifier: Modifier = Modifier,
    userName: String,
    userPhoto: Painter
){
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(80f)
            ){
                Text(
                    text = buildAnnotatedString {
                        val boldStyle = SpanStyle(
                            fontWeight = FontWeight.Bold
                        )
                        pushStyle(boldStyle)
                        append("Привет, ")
                        pop()
                        append("$userName!")
                    },
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "У нас много новинок",
                    fontSize = 15.sp,
                )
            }
            RoundImage(
                userPhoto = userPhoto,
                modifier = Modifier
                    .size(50.dp)
                    .weight(20f)
            )
        }

        Spacer(modifier = Modifier.height(30.dp))
    }
}