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
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import com.example.myapplication.util.UiEvent
import com.example.myapplication.register.registerUser
import com.example.myapplication.ui.theme.Purple40
import com.example.myapplication.ui.theme.Purple80


@Composable
//fun RegisterScreen(onNavigate: (UiEvent.Navigate) -> Unit)
fun RegisterScreen(){
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 35.dp)
            .padding(top = 10.dp)
    ){
        Text(
            text = "Добро пожаловать!",
            fontSize = 19.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        val login = remember{mutableStateOf("")}
        val password = remember{mutableStateOf("")}
        val username = remember{mutableStateOf("")}
        Row {

            Column {
                Text(
                    text = "Логин",
                    fontSize = 17.sp,
                )
                BasicTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(20.dp))
                        .border(
                            width = 2.dp,
                            brush = Brush.verticalGradient(listOf(Purple80, Purple40)),
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
                    fontSize = 19.sp,
                )
                TextField(
                    password.value,
                    {password.value = it},
                )

                Spacer(modifier = Modifier
                    .height(25.dp)
                )

                Text(
                    text = "Имя пользователя",
                    fontSize = 19.sp,
                )
                TextField(
                    username.value,
                    {username.value = it},
                )
            }
        }

        Spacer(modifier = Modifier.height(25.dp))
        Button(onClick = {registerUser(username.value, login.value, password.value)},
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier.padding(10.dp),
        ){ Text("Зарегистрироваться", fontSize = 20.sp) }
        Spacer(modifier = Modifier
            .height(60.dp)
        )
    }
}