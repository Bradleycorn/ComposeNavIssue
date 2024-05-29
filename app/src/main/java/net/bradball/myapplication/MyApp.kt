package net.bradball.myapplication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import kotlinx.coroutines.delay

/**
 * MyAppState handles the primary State for the application as a whole
 */
class MyAppState(
    val navController: NavHostController
) {
    /**
     * isLoggedIn keeps track of the user login state.
     *
     * In a real app, this value could change at any time and
     * as a result of many different things (such as a session timing out, clicking a logout button, etc).
     */
    var isLoggedIn by mutableStateOf(false)
}

@Composable
fun rememberAppState(navController: NavHostController = rememberNavController()): MyAppState {
    return remember { MyAppState(navController) }
}


@Composable
fun MyApp(appState: MyAppState = rememberAppState()) {
    /**
     * The user cannot access the home screen unless/until they are logged in.
     * In addition, if/when their login state changes and they are no longer logged in,
     * then we want to leave the home screen and show the login screen.
     */
    LaunchedEffect(appState.isLoggedIn) {
        if (!appState.isLoggedIn) {
            appState.navController.navigateToLogin()
        }
    }

    Scaffold(modifier = Modifier.fillMaxSize()) {
        MyNavHost(
            navController = appState.navController,
            onLoginComplete = { /*TODO*/ },
            modifier = Modifier.padding(it).fillMaxSize())
    }
}
