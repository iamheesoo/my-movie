package com.heesoo.my_movie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.heesoo.my_movie.domain.model.Movie
import com.heesoo.my_movie.presentaion.DetailListener
import com.heesoo.my_movie.presentaion.HomeListener
import com.heesoo.my_movie.presentaion.detail.DetailScreen
import com.heesoo.my_movie.presentaion.favorite.FavoriteScreen
import com.heesoo.my_movie.presentaion.home.HomePage
import com.heesoo.my_movie.presentaion.SearchListener
import com.heesoo.my_movie.presentaion.navigation.Route
import com.heesoo.my_movie.presentaion.navigation.bottomBarRouteNameSet
import com.heesoo.my_movie.presentaion.navigation.composable.BottomNavigationBar
import com.heesoo.my_movie.presentaion.search.SearchScreen
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
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route
    val isVisibleBottomBar = currentRoute in bottomBarRouteNameSet

    Scaffold(
        modifier = modifier,
        bottomBar = { if (isVisibleBottomBar) BottomNavigationBar(navController = navController) }
    ) { innerPadding ->
        MainNavHost(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
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

                    override fun goToSearch() {
                        navController.navigate(Route.Search)
                    }
                }
            )
        }
        composable<Route.Detail> {
            DetailScreen(
                viewModel = hiltViewModel(),
                listener = object : DetailListener {
                    override fun popBackStack() {
                        navController.popBackStack()
                    }
                }
            )
        }
        composable<Route.Search> {
            SearchScreen(
                viewModel = hiltViewModel(),
                listener = object : SearchListener {
                    override fun goToDetail(movie: Movie) {
                        navController.navigate(Route.Detail(movieId = movie.id))
                    }

                    override fun popBackStack() {
                        navController.popBackStack()
                    }
                }
            )
        }
        composable<Route.Favorite> {
            FavoriteScreen(viewModel = hiltViewModel())
        }
    }
}
