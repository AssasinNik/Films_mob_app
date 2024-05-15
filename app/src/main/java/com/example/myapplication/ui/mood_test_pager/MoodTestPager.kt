package com.example.myapplication.ui.mood_test_pager

import android.util.Log
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
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import com.example.myapplication.data.questions_data.Question
import com.example.myapplication.ui.theme.testMoodPagerClickedColor
import com.example.myapplication.ui.theme.testMoodPagerColor
import com.example.myapplication.util.UiEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MoodTestPager(
    onPopBackStack: () -> Unit,
    viewModel: MoodTestPagerViewModel = hiltViewModel()
){
    val scope: CoroutineScope = rememberCoroutineScope()

    val question1 = viewModel.question1.collectAsState(initial = emptyList())
    val question2 = viewModel.question2.collectAsState(initial = emptyList())
    val question3 = viewModel.question3.collectAsState(initial = emptyList())
    val question4 = viewModel.question4.collectAsState(initial = emptyList())
    val question5 = viewModel.question5.collectAsState(initial = emptyList())

    val questions = listOf(
        question1,
        question2,
        question3,
        question4,
        question5
    )

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

        Row(modifier = Modifier
            .fillMaxWidth(),
        ) {
            Button(onClick = {
                scope.launch {
                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                }
            }) {
                Text(text = "Далее")
            }
        }

        HorizontalPager(
            userScrollEnabled = false,
            state = pagerState,
        ) {index ->
            if (questions[0].value.isNotEmpty() && questions[1].value.isNotEmpty() && questions[2].value.isNotEmpty() &&
                questions[3].value.isNotEmpty() && questions[4].value.isNotEmpty()) {
                QuestionPage(question = questions[index].value[0])
            }
        }
    }
}