package com.heesoo.my_movie.presentaion.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Star
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavigationRoute<T : Any>(
    val title: String,
    val route: T,
    val icon: ImageVector
)

val bottomNavigationRouteList = listOf(
    BottomNavigationRoute(title = "홈", route = Route.Home, icon = Icons.Rounded.Home),
    BottomNavigationRoute(title = "찜", route = Route.Favorite, icon = Icons.Rounded.Star)
)

val bottomBarRouteNameSet = bottomNavigationRouteList
    .mapNotNull { it.route::class.qualifiedName }
    .toSet()
