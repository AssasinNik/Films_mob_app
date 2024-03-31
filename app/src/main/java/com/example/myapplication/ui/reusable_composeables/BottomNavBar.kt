package com.example.myapplication.ui.reusable_composeables


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.myapplication.R

data class BottomNavBarItem(
    val selectedIcon: Painter,
    val unselectedIcon: Painter
)

@Composable
fun BottomNavBar(){
    val items = listOf(
        BottomNavBarItem(
            selectedIcon = painterResource(id = R.drawable.icon_home_selected),
            unselectedIcon = painterResource(id = R.drawable.icon_home_unselected)
        ),
        BottomNavBarItem(
            selectedIcon = painterResource(id = R.drawable.icon_play_selected),
            unselectedIcon = painterResource(id = R.drawable.icon_play_unselected)
        ),
        BottomNavBarItem(
            selectedIcon = painterResource(id = R.drawable.icon_user_selected),
            unselectedIcon = painterResource(id = R.drawable.icon_user_unselected)
        )
    )

    var selectedItemIndex by rememberSaveable {
        mutableStateOf(0)
    }

    Scaffold(
        bottomBar = {
            NavigationBar (

            ){
                items.forEachIndexed { index, item->
                    NavigationBarItem(
                        selected = selectedItemIndex == index,
                        onClick = {
                            selectedItemIndex = index
                        },
                        icon = {
                            Box(
                                modifier = Modifier
                                    .height(25.dp)
                                    .width(25.dp)
                            ){
                                Icon(
                                    painter = if (index == selectedItemIndex){
                                        item.selectedIcon
                                    }else item.unselectedIcon,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxSize()
                                )

                            }
                        }
                    )
                }
            }
        }
    ){

    }
}