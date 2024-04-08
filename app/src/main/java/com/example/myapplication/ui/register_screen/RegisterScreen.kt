package com.example.myapplication.ui.register_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.util.UiEvent
import com.example.myapplication.register.registerUser


@Composable
fun RegisterScreen(onNavigate: (UiEvent.Navigate) -> Unit){
    Column (
        modifier = Modifier
            .fillMaxWidth()
    ){
        Text(
            modifier = Modifier.padding(horizontal = 75.dp),
            text = "Добро пожаловать!!!",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(60.dp))
        val login = remember{mutableStateOf("")}
        Text(
            modifier = Modifier.padding(horizontal = 25.dp),
            text = "Логин",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.height(25.dp))
        TextField(
            login.value,
            {login.value = it},
        )
        val password = remember{mutableStateOf("")}
        Text(
            modifier = Modifier.padding(horizontal = 25.dp),
            text = "Пароль",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.height(25.dp))
        TextField(
            password.value,
            {password.value = it},
        )
        val username = remember{mutableStateOf("")}
        Text(
            modifier = Modifier.padding(horizontal = 25.dp),
            text = "Имя пользователя",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.height(25.dp))
        TextField(
            username.value,
            {username.value = it},
        )
        Spacer(modifier = Modifier.height(25.dp))
        Button(onClick = {registerUser(username.value, login.value, password.value)},
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier.padding(10.dp)
        ){ Text("Зарегистрироваться", fontSize = 20.sp) }
    }
}