package com.example.myapplication.ui.reusable_composeables


import android.annotation.SuppressLint
import android.provider.Telephony.Mms.Addr
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.myapplication.ui.theme.navBarBallColor
import com.example.myapplication.ui.theme.navBarColor
import com.example.myapplication.ui.theme.primaryGradientTBottom
import com.example.myapplication.ui.theme.primaryGradientTop
import com.example.myapplication.util.Routes
import com.example.myapplication.util.UiEvent
import com.exyte.animatednavbar.AnimatedNavigationBar
import com.exyte.animatednavbar.animation.balltrajectory.Parabolic
import com.exyte.animatednavbar.animation.indendshape.Height
import com.exyte.animatednavbar.animation.indendshape.shapeCornerRadius
import com.exyte.animatednavbar.utils.noRippleClickable



@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.noRippleClickable(onClick: () -> Unit): Modifier = composed {
    clickable(
        indication = null,
        interactionSource = remember { MutableInteractionSource() }) {
        onClick()
    }
}

@Composable
fun BottomNavBar(
    navController: NavHostController
){
    val navigationBarItems = remember {
        BottomNavBarItem.values()
    }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currenDestination = navBackStackEntry?.destination

    var selectedItemIndex by remember {
        mutableStateOf(0)
    }
    if (currenDestination?.route in setOf(Routes.MAIN_SCREEN, Routes.MOVIE_LIST_SCREEN, Routes.USER_SCREEN)) {
        AnimatedNavigationBar(
            modifier = Modifier.height(64.dp),
            selectedIndex = checkCurrentDestination(currenDestination?.route),
            cornerRadius = shapeCornerRadius(cornerRadius = 34.dp),
            ballAnimation = Parabolic(tween(300)),
            indentAnimation = Height(tween(300)),
            barColor = navBarColor,
            ballColor = navBarBallColor
        ) {
            navigationBarItems.forEach { screen ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .noRippleClickable {
                            selectedItemIndex = screen.ordinal
                            navController.navigate(screen.route)
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        modifier = Modifier.size(26.dp),
                        imageVector = screen.icon,
                        contentDescription = "Bottom Var Icon"
                    )
                }
            }
        }
    }
}

enum class BottomNavBarItem(val icon: ImageVector, val route: String) {
    Home(
        icon = Icons.Default.Home,
        route = Routes.MAIN_SCREEN
    ),
    List(
        icon = Icons.Default.PlayArrow,
        route = Routes.MOVIE_LIST_SCREEN
    ),
    User(
        icon = Icons.Default.AccountCircle,
        route = Routes.USER_SCREEN
    )
}

fun checkCurrentDestination(currentScreen: String?): Int {
    when (currentScreen){
        Routes.MAIN_SCREEN -> return 0
        Routes.MOVIE_LIST_SCREEN -> return 1
        Routes.USER_SCREEN -> return 2
        else -> return 0
    }
}

