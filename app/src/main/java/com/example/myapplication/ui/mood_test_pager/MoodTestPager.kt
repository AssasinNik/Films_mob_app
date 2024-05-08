package com.example.myapplication.ui.mood_test_pager

import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.example.myapplication.R
import com.example.myapplication.ui.theme.testMoodPagerClickedColor
import com.example.myapplication.ui.theme.testMoodPagerColor
import com.example.myapplication.util.UiEvent

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MoodTestPager(
    onPopBackStack: () -> Unit,
    viewModel: MoodTestPagerViewModel = hiltViewModel()
){
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.PopBackStack -> onPopBackStack()
                else -> Unit
            }
        }

    }
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
                Icon(
                    modifier = Modifier
                        .clickable {
                            viewModel.onEvent(MoodTestPagerEvent.onCloseIconClick)
                        },
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close Icon"
                )
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
                            color = testMoodPagerColor,
                            shape = RoundedCornerShape(15.dp)
                        )
                        .padding(vertical = 15.dp, horizontal = 4.dp)
                ) {
                    Column (
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Text(
                            text = "Как выглядит ваш идеальный вечер?",
                            fontSize = 17.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                var backgroundColor = remember {
                    mutableStateOf(testMoodPagerColor)
                }
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            if (backgroundColor.value == testMoodPagerColor) {
                                backgroundColor.value = testMoodPagerClickedColor
                            } else {
                                backgroundColor.value = testMoodPagerColor
                            }
                        }
                        .background(
                            color = backgroundColor.value,
                            shape = RoundedCornerShape(15.dp),
                        )
                        .padding(vertical = 15.dp, horizontal = 15.dp)


                ) {
                    Column (
                        modifier = Modifier
                            .fillMaxWidth(),
                            horizontalAlignment = Alignment.Start
                    ){
                        Text(
                            textAlign = TextAlign.Start,
                            text = "Пешая прогулка в одиночестве",
                            fontSize = 15.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = testMoodPagerColor,
                            shape = RoundedCornerShape(15.dp)
                        )
                        .padding(vertical = 15.dp, horizontal = 15.dp)
                ) {
                    Column (
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.Start
                    ){
                        Text(
                            textAlign = TextAlign.Start,
                            text = "Пешая прогулка в одиночестве",
                            fontSize = 15.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = testMoodPagerColor,
                            shape = RoundedCornerShape(15.dp)
                        )
                        .padding(vertical = 15.dp, horizontal = 15.dp)
                ) {
                    Column (
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.Start
                    ){
                        Text(
                            textAlign = TextAlign.Start,
                            text = "Пешая прогулка в одиночестве",
                            fontSize = 15.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = testMoodPagerColor,
                            shape = RoundedCornerShape(15.dp)
                        )
                        .padding(vertical = 15.dp, horizontal = 15.dp)
                ) {
                    Column (
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.Start
                    ){
                        Text(
                            textAlign = TextAlign.Start,
                            text = "Пешая прогулка в одиночестве",
                            fontSize = 15.sp
                        )
                    }
                }
            }
        }
    }
}