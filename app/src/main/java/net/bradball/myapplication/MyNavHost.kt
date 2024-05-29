package net.bradball.myapplication

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions


@Composable
fun MyNavHost(navController: NavHostController = rememberNavController(), onLoginComplete: ()->Unit, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = "home",
        modifier = modifier
    ) {

        composable(route = "home") {
            HomeScreen(onLogoutClicked = {
                 navController.navigateToLogin()
            })
        }

        composable(route = "login") {
            LoginScreen(onLoginClick = {
                onLoginComplete()
                navController.navigateToHome()
            })
        }
    }
}


/**
 * Navigate to login.
 * Pops all destinations from the backstack, making login
 * the root destination.
 */
fun NavHostController.navigateToLogin() {

    val rootDestination = this.graph.findStartDestination().id

    navigate(
        route = "login",
        navOptions = navOptions {
            popUpTo(rootDestination) { inclusive = true }
        }
    )
}

/**
 * Navigate to home.
 * Pops all destinations from the backstack, making home
 * the root destination.
 */
fun NavHostController.navigateToHome() {

    val rootDestination = this.graph.findStartDestination().id

    navigate(
        route = "home",
        navOptions = navOptions {
            popUpTo(rootDestination) { inclusive = true }
        }
    )
}