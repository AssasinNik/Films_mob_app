package com.example.myapplication.ui.user_screen

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import coil.compose.rememberImagePainter
import com.example.myapplication.register.registerUser
import com.example.myapplication.ui.reusable_composeables.RoundImage
import com.example.myapplication.ui.theme.primaryGradientTBottom
import com.example.myapplication.ui.theme.primaryGradientTop

@Composable
fun UserScreen(
    onPopBackStack: () -> Unit
){
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 35.dp)
            .padding(top = 30.dp)
    ){
        Row {
             Column (
                 horizontalAlignment = Alignment.CenterHorizontally
             ) {
                 RoundImage(
                     userPhoto = rememberImagePainter("https://i.postimg.cc/wM50wWJ3/2024-03-30-131955798.png"),
                     modifier = Modifier
                         .size(90.dp)
                         .padding(bottom = 5.dp)
                 )
                 Text(text = "Гигачадыч",
                     fontSize = 19.sp,
                     fontWeight = FontWeight.Bold
                 )
             }
        }


        val login = remember{ mutableStateOf("") }
        val password = remember{ mutableStateOf("") }
        val username = remember{ mutableStateOf("") }
        Row {
            Column {
                Text(
                    text = "Логин",
                    fontSize = 17.sp,
                    modifier = Modifier
                        .padding(bottom = 9.dp)
                )
                BasicTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(20.dp))
                        .border(
                            width = 2.dp,
                            brush = Brush.verticalGradient(
                                listOf(
                                    primaryGradientTop,
                                    primaryGradientTBottom
                                )
                            ),
                            shape = RoundedCornerShape(20.dp)
                        )
                        .padding(13.dp),
                    textStyle = TextStyle(color = Color.White, fontSize = 15.sp),
                    cursorBrush = Brush.verticalGradient(listOf(Color.White, Color.White)),
                    singleLine = true,
                    value = login.value,
                    onValueChange = {
                        login.value = it
                    },
                )

                Spacer(modifier = Modifier
                    .height(25.dp)
                )

                Text(
                    text = "Пароль",
                    fontSize = 17.sp,
                    modifier = Modifier
                        .padding(bottom = 9.dp)
                )
                BasicTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(20.dp))
                        .border(
                            width = 2.dp,
                            brush = Brush.verticalGradient(
                                listOf(
                                    primaryGradientTop,
                                    primaryGradientTBottom
                                )
                            ),
                            shape = RoundedCornerShape(20.dp)
                        )
                        .padding(13.dp),
                    textStyle = TextStyle(color = Color.White, fontSize = 15.sp),
                    cursorBrush = Brush.verticalGradient(listOf(Color.White, Color.White)),
                    singleLine = true,
                    value = password.value,
                    onValueChange = {
                        password.value = it
                    },
                )

                Spacer(modifier = Modifier
                    .height(25.dp)
                )

                Text(
                    text = "Новый пароль",
                    fontSize = 17.sp,
                    modifier = Modifier
                        .padding(bottom = 9.dp)
                )
                BasicTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(20.dp))
                        .border(
                            width = 2.dp,
                            brush = Brush.verticalGradient(
                                listOf(
                                    primaryGradientTop,
                                    primaryGradientTBottom
                                )
                            ),
                            shape = RoundedCornerShape(20.dp)
                        )
                        .padding(13.dp),
                    textStyle = TextStyle(color = Color.White, fontSize = 15.sp),
                    cursorBrush = Brush.verticalGradient(listOf(Color.White, Color.White)),
                    singleLine = true,
                    value = username.value,
                    onValueChange = {
                        username.value = it
                    },
                )
            }
        }

        Button(onClick = {
            registerUser(
            username.value,
            login.value,
            password.value
        )
        },
            colors = ButtonDefaults.buttonColors(Color.Transparent),
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        listOf(
                            primaryGradientTop,
                            primaryGradientTBottom
                        )
                    ),
                    shape = RoundedCornerShape(12.dp)
                )
        ){ Text(
            "Изменить данные",
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            color = Color.White
        ) }

        Spacer(modifier = Modifier
            .height(60.dp)
        )
    }
}