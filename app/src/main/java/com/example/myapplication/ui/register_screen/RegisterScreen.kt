package com.example.myapplication.ui.register_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myapplication.ui.movie_screen.MovieScreenViewModel
import com.example.myapplication.ui.theme.primaryGradientTBottom
import com.example.myapplication.ui.theme.primaryGradientTop
import com.example.myapplication.ui.register_screen.RegisterScreenViewModel
import com.example.myapplication.util.UiEvent

@Composable
fun RegisterScreen(
    viewModel: RegisterScreenViewModel = hiltViewModel(),
    onNavigate: (UiEvent.Navigate) -> Unit
) {
    
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect{ event ->
            when(event) {
                is UiEvent.Navigate -> onNavigate(event)
                else -> Unit
            }
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 35.dp)
            .padding(top = 10.dp)
    ) {
        Text(
            text = "Регистрация",
            fontSize = 19.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Row {
            Column {
                Text(
                    text = "Логин",
                    fontSize = 17.sp,
                    modifier = Modifier.padding(bottom = 9.dp)
                )
                BasicTextField(
                    value = viewModel.login,
                    onValueChange = {
                        viewModel.onEvent(RegisterScreenEvent.OnLoginChange(it))
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(20.dp))
                        .border(
                            width = 2.dp,
                            brush = Brush.verticalGradient(
                                listOf(primaryGradientTop, primaryGradientTBottom)
                            ),
                            shape = RoundedCornerShape(20.dp)
                        )
                        .padding(13.dp),
                    textStyle = TextStyle(color = Color.White, fontSize = 15.sp),
                    singleLine = true,
                    cursorBrush = Brush.verticalGradient(listOf(Color.White, Color.White))
                )

                Spacer(modifier = Modifier.height(25.dp))

                Text(
                    text = "Пароль",
                    fontSize = 17.sp,
                    modifier = Modifier.padding(bottom = 9.dp)
                )
                BasicTextField(
                    value = viewModel.password,
                    onValueChange = {
                        viewModel.onEvent(RegisterScreenEvent.OnPasswordChange(it))
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(20.dp))
                        .border(
                            width = 2.dp,
                            brush = Brush.verticalGradient(
                                listOf(primaryGradientTop, primaryGradientTBottom)
                            ),
                            shape = RoundedCornerShape(20.dp)
                        )
                        .padding(13.dp),
                    textStyle = TextStyle(color = Color.White, fontSize = 15.sp),
                    singleLine = true,
                    cursorBrush = Brush.verticalGradient(listOf(Color.White, Color.White))
                )

                Spacer(modifier = Modifier.height(25.dp))

                Text(
                    text = "Имя пользователя",
                    fontSize = 17.sp,
                    modifier = Modifier.padding(bottom = 9.dp)
                )
                BasicTextField(
                    value = viewModel.name,
                    onValueChange = {
                        viewModel.onEvent(RegisterScreenEvent.OnNameChange(it))
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(20.dp))
                        .border(
                            width = 2.dp,
                            brush = Brush.verticalGradient(
                                listOf(primaryGradientTop, primaryGradientTBottom)
                            ),
                            shape = RoundedCornerShape(20.dp)
                        )
                        .padding(13.dp),
                    textStyle = TextStyle(color = Color.White, fontSize = 15.sp),
                    singleLine = true,
                    cursorBrush = Brush.verticalGradient(listOf(Color.White, Color.White))
                )
            }
        }

        Spacer(modifier = Modifier.height(25.dp))

        Button(
            onClick = {
                viewModel.onEvent(RegisterScreenEvent.OnRegisterClick)
            },
            colors = ButtonDefaults.buttonColors(Color.Transparent),
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        listOf(primaryGradientTop, primaryGradientTBottom)
                    ),
                    shape = RoundedCornerShape(12.dp)
                )
        ) {
            Text(
                "Зарегистрироваться",
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                color = Color.White
            )
        }
        
        Text(text = "Уже есть аккаунт?")

        Spacer(modifier = Modifier.height(60.dp))
    }
}
