package com.example.myapplication.ui.mood_test_pager

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.myapplication.R

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MoodTestPager(

){
    val pagerState = rememberPagerState(
        initialPage = 0,
    ) {
        5
    }
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 15.dp)
    ){
        Row (
            modifier = Modifier
                .padding(bottom = 25.dp)
                .padding(horizontal = 20.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.End
            ){
                Icon(imageVector = Icons.Default.Close, contentDescription = "Close Icon")
            }
        }

        HorizontalPager(
            state = pagerState,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp)
            ) {

                Card (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 30.dp),
                    shape = RoundedCornerShape(15.dp),
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.test_image),
                            contentDescription = "movie_poster",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    }
                }
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = Color.Black,
                            shape = RoundedCornerShape(15.dp)
                        )
                        .padding(vertical = 15.dp, horizontal = 5.dp)
                ) {
                    Column (
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Text(
                            text = "Как выглядит ваш идеальный вечер?",
                            fontSize = 17.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(15.dp))

                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = Color.Black,
                            shape = RoundedCornerShape(15.dp)
                        )
                        .padding(vertical = 15.dp, horizontal = 5.dp)
                ) {
                    Text(
                        text = "Пешая прогулка в одиночестве",
                        fontSize = 15.sp
                    )
                }

                Spacer(modifier = Modifier.height(6.dp))

                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = Color.Black,
                            shape = RoundedCornerShape(15.dp)
                        )
                        .padding(vertical = 15.dp, horizontal = 5.dp)
                ){
                    Text(
                        text = "Качаца",
                        fontSize = 15.sp
                    )
                }

                Spacer(modifier = Modifier.height(6.dp))

                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = Color.Black,
                            shape = RoundedCornerShape(15.dp)
                        )
                        .padding(vertical = 15.dp, horizontal = 5.dp)
                ){
                    Text(
                        text = "Романтичный ужин с любимым",
                        fontSize = 15.sp
                    )

                }

                Spacer(modifier = Modifier.height(6.dp))

                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = Color.Black,
                            shape = RoundedCornerShape(15.dp)
                        )
                        .padding(vertical = 15.dp)
                        .padding(start = 5.dp)
                ){
                    Text(
                        text = "Констр страйк гонка вооружений",
                        fontSize = 15.sp
                    )
                }
            }
        }
    }
}