package com.example.myapplication.ui.edit_user_data

import android.annotation.SuppressLint
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.example.myapplication.R
import com.example.myapplication.ui.theme.backgroundColor
import com.example.myapplication.ui.theme.primaryGradientTBottom
import com.example.myapplication.ui.theme.primaryGradientTop
import com.example.myapplication.util.UiEvent
import java.io.File
import androidx.compose.material3.TextFieldColors as TextFieldColors1

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun EditUserData(
    onNavigate: (UiEvent.Navigate) -> Unit,
    onPopBackStack: () -> Unit,
    viewModel: EditUserDataViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect{event ->
            when(event){
                is UiEvent.Navigate -> onNavigate(event)
                is UiEvent.PopBackStack -> onPopBackStack()
                else -> Unit
            }
        }
    }

    val photoPickerLauncher = rememberLauncherForActivityResult(contract = ActivityResultContracts.PickVisualMedia()) {uri: Uri? ->
        uri?.let {
            viewModel.avatar = uri.toString()
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        floatingActionButton = {
            ExtendedFloatingActionButton(
                containerColor = primaryGradientTop,
                onClick = {
                    viewModel.onEvent(EditUserDataEvent.OnSaveChangesClick)
                },
                icon = {
                    Icon(imageVector = Icons.Filled.Check, contentDescription = "Save")
                },
                text = {
                    Text(text = "Сохранить")
                }
            )
        }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 10.dp)
                .padding(horizontal = 30.dp)
        ){
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
            ) {
                Icon(
                    modifier = Modifier
                        .clickable {
                            viewModel.onEvent(EditUserDataEvent.OnBackIconClick)
                        },
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back"
                )
            }
            Text(
                text = "Имя & Фото",
                fontSize = 18.sp
            )

            Spacer(modifier = Modifier.height(40.dp))

            AsyncImage(
                model = Uri.parse(viewModel.avatar),
                contentDescription = "User photo",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(200.dp)
                    .aspectRatio(1f, matchHeightConstraintsFirst = true)
                    .border(
                        width = 2.dp,
                        brush = Brush.verticalGradient(
                            listOf(
                                primaryGradientTop,
                                primaryGradientTBottom
                            )
                        ),
                        shape = CircleShape
                    )
                    .clip(CircleShape)
                    .clickable {
                        photoPickerLauncher.launch(
                            PickVisualMediaRequest()
                        )
                    }
            )

            Spacer(modifier = Modifier.height(20.dp))

            TextField(
                value = viewModel.name,
                onValueChange = {
                    viewModel.onEvent(EditUserDataEvent.OnUserNameChange(it))
                },
                label = {
                    Text(text = "Имя")
                },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                    focusedIndicatorColor = primaryGradientTop,
                    unfocusedIndicatorColor = primaryGradientTop,
                    focusedLabelColor = primaryGradientTop,
                    unfocusedLabelColor = primaryGradientTop,
                    cursorColor = Color.White,
                ),
                singleLine = true
            )
        }
    }
}