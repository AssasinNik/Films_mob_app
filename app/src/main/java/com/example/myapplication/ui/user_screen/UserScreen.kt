package com.example.myapplication.ui.user_screen

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.example.myapplication.ui.reusable_composeables.RoundImage
import com.example.myapplication.ui.theme.primaryGradientTBottom
import com.example.myapplication.ui.theme.primaryGradientTop
import com.example.myapplication.ui.theme.textFieldBackground
import com.example.myapplication.util.UiEvent

@Composable
fun UserScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    onPopBackStack: () -> Unit,
    viewModel: UserScreenViewModel = hiltViewModel()
){
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect{event ->
            when(event){
                is UiEvent.Navigate -> onNavigate(event)
                else -> Unit
            }
        }
    }

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
                     model = Uri.parse(viewModel.avatar),
                     modifier = Modifier
                         .size(90.dp)
                         .padding(bottom = 5.dp)
                         .clickable {
                             viewModel.onEvent(UserScreenEvent.OnEditUserDataClick)
                         }
                 )
                 Row(
                     horizontalArrangement = Arrangement.Center,
                     verticalAlignment = Alignment.CenterVertically,
                     modifier = Modifier
                         .fillMaxWidth()
                 ) {
                     Text(
                         modifier = Modifier
                             .clickable {
                                 viewModel.onEvent(UserScreenEvent.OnEditUserDataClick)
                             },
                         text = viewModel.name,
                         fontSize = 19.sp,
                         fontWeight = FontWeight.Bold
                     )

                     Spacer(modifier = Modifier.width(8.dp))

                     Icon(
                         modifier = Modifier
                             .height(18.dp)
                             .clickable {
                                 viewModel.onEvent(UserScreenEvent.OnEditUserDataClick)
                             },
                         imageVector = Icons.Default.Edit,
                         contentDescription = "Edit"
                     )
                 }
             }
        }

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
                        .background(
                            color = textFieldBackground,
                            shape = RoundedCornerShape(20.dp)
                        )
                        .padding(13.dp),
                    textStyle = TextStyle(color = Color.White, fontSize = 15.sp),
                    cursorBrush = Brush.verticalGradient(listOf(Color.White, Color.White)),
                    singleLine = true,
                    value = viewModel.login,
                    onValueChange = {
                        viewModel.onEvent(UserScreenEvent.OnLoginChange(it))
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
                        .background(
                            color = textFieldBackground,
                            shape = RoundedCornerShape(20.dp)
                        )
                        .padding(13.dp),
                    textStyle = TextStyle(color = Color.White, fontSize = 15.sp),
                    cursorBrush = Brush.verticalGradient(listOf(Color.White, Color.White)),
                    singleLine = true,
                    value = viewModel.insertPassword,
                    onValueChange = {
                        viewModel.onEvent(UserScreenEvent.OnPasswordChange(it))
                    },
                    visualTransformation = PasswordVisualTransformation()
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
                        .background(
                            color = textFieldBackground,
                            shape = RoundedCornerShape(20.dp)
                        )
                        .padding(13.dp),
                    textStyle = TextStyle(color = Color.White, fontSize = 15.sp),
                    cursorBrush = Brush.verticalGradient(listOf(Color.White, Color.White)),
                    singleLine = true,
                    value = viewModel.newPasswords,
                    onValueChange = {
                        viewModel.onEvent(UserScreenEvent.OnNewPasswordChange(it))
                    },
                )
            }
        }

        Button(onClick = {(
            viewModel.onEvent(UserScreenEvent.OnSaveChangesClick)
        )},
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

class PasswordVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        return TransformedText(
            buildAnnotatedString {
                text.forEach {
                    append("*")
                }
            },
            OffsetMapping.Identity
        )
    }
}
