package com.example.myapplication.ui.reusable_composeables

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.ui.theme.Purple40
import com.example.myapplication.ui.theme.primaryGradientTBottom
import com.example.myapplication.ui.theme.primaryGradientTop

@Composable
fun RoundImage(
    userPhoto: Painter,
    modifier: Modifier = Modifier
){
    Image(
        painter = userPhoto,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
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

    )
}