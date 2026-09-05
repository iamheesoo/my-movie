package com.heesoo.my_movie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.heesoo.my_movie.domain.model.Movie
import com.heesoo.my_movie.presentaion.HomeListener
import com.heesoo.my_movie.presentaion.detail.DetailScreen
import com.heesoo.my_movie.presentaion.home.HomePage
import com.heesoo.my_movie.presentaion.navigation.Route
import com.heesoo.my_movie.presentaion.ui.theme.MymovieTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MymovieTheme {
                MainScreen(modifier = Modifier.safeDrawingPadding())
            }
        }
    }
}

@Composable
private fun MainScreen(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    MainNavHost(navController = navController, modifier = modifier)
}

@Composable
private fun MainNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = Route.Home,
        modifier = modifier
    ) {
        composable<Route.Home> {
            HomePage(
                viewModel = hiltViewModel(),
                listener = object : HomeListener {
                    override fun goToDetail(movie: Movie) {
                        navController.navigate(Route.Detail(movieId = movie.id))
                    }
                }
            )
        }
        composable<Route.Detail> { backStackEntry ->
            val route = backStackEntry.toRoute<Route.Detail>()
            DetailScreen(movieId = route.movieId)
        }
    }
}
