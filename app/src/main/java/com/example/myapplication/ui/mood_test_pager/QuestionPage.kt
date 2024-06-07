package com.example.myapplication.ui.mood_test_pager

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.example.myapplication.data.questions_data.Question
import com.example.myapplication.ui.theme.testMoodPagerClickedColor
import com.example.myapplication.ui.theme.testMoodPagerColor
import kotlinx.coroutines.launch

@Composable
fun QuestionPage(
    questionNumber: Int,
    question: Question,
    viewModel: MoodTestPagerViewModel = hiltViewModel()
) {
    var selectedAnswerIndex by remember { mutableStateOf(-1) }

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
                    painter = rememberImagePainter(data = question.picture),
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
                    text = question.question,
                    fontSize = 17.sp,
                    textAlign = TextAlign.Center
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = if (selectedAnswerIndex == 0) Color.Gray else testMoodPagerColor,
                    shape = RoundedCornerShape(15.dp)
                )
                .padding(vertical = 15.dp, horizontal = 15.dp)
                .clickable {
                    when (questionNumber){
                        0 -> viewModel.answer1 = question.genresOfAnswer1 ?: ""
                        1 -> viewModel.answer2 = question.genresOfAnswer1 ?: ""
                        2 -> viewModel.answer3 = question.genresOfAnswer1 ?: ""
                        3 -> viewModel.answer4 = question.genresOfAnswer1 ?: ""
                        4 -> viewModel.answer5 = question.genresOfAnswer1 ?: ""
                    }
                    selectedAnswerIndex = 0
                }
        ) {
            Column (
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ){
                Text(
                    textAlign = TextAlign.Start,
                    text = question.answer1 ?: "",
                    fontSize = 15.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = if (selectedAnswerIndex == 1) Color.Gray else testMoodPagerColor,
                    shape = RoundedCornerShape(15.dp)
                )
                .padding(vertical = 15.dp, horizontal = 15.dp)
                .clickable {
                    when (questionNumber){
                        0 -> viewModel.answer1 = question.genresOfAnswer2 ?: ""
                        1 -> viewModel.answer2 = question.genresOfAnswer2 ?: ""
                        2 -> viewModel.answer3 = question.genresOfAnswer2 ?: ""
                        3 -> viewModel.answer4 = question.genresOfAnswer2 ?: ""
                        4 -> viewModel.answer5 = question.genresOfAnswer2 ?: ""
                    }
                    selectedAnswerIndex = 1
                }
        ) {
            Column (
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ){
                Text(
                    textAlign = TextAlign.Start,
                    text = question.answer2 ?: "",
                    fontSize = 15.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = if (selectedAnswerIndex == 2) Color.Gray else testMoodPagerColor,
                    shape = RoundedCornerShape(15.dp)
                )
                .padding(vertical = 15.dp, horizontal = 15.dp)
                .clickable {
                    when (questionNumber){
                        0 -> viewModel.answer1 = question.genresOfAnswer3 ?: ""
                        1 -> viewModel.answer2 = question.genresOfAnswer3 ?: ""
                        2 -> viewModel.answer3 = question.genresOfAnswer3 ?: ""
                        3 -> viewModel.answer4 = question.genresOfAnswer3 ?: ""
                        4 -> viewModel.answer5 = question.genresOfAnswer3 ?: ""
                    }
                    selectedAnswerIndex = 2
                }
        ) {
            Column (
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ){
                Text(
                    textAlign = TextAlign.Start,
                    text = question.answer3 ?: "",
                    fontSize = 15.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = if (selectedAnswerIndex == 3) Color.Gray else testMoodPagerColor,
                    shape = RoundedCornerShape(15.dp)
                )
                .padding(vertical = 15.dp, horizontal = 15.dp)
                .clickable {
                    when (questionNumber){
                        0 -> viewModel.answer1 = question.genresOfAnswer4 ?: ""
                        1 -> viewModel.answer2 = question.genresOfAnswer4 ?: ""
                        2 -> viewModel.answer3 = question.genresOfAnswer4 ?: ""
                        3 -> viewModel.answer4 = question.genresOfAnswer4 ?: ""
                        4 -> viewModel.answer5 = question.genresOfAnswer4 ?: ""
                    }
                    selectedAnswerIndex = 3
                }
        ) {
            Column (
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ){
                Text(
                    textAlign = TextAlign.Start,
                    text = question.answer4 ?: "",
                    fontSize = 15.sp
                )
            }
        }

    }
}