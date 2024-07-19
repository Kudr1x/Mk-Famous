package org.github.kudrix.famous.org.github.kudrix.famous.theme

import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.github.kudrix.famous.theme.features.create.CreatePostScreen
import org.github.kudrix.famous.theme.features.login.LoginScreen
import org.github.kudrix.famous.theme.features.paywall.PaywallScreen
import org.github.kudrix.famous.theme.navigation.AppScreens
import org.github.kudrix.famous.theme.navigation.LocalNavHost
import org.github.kudrix.famous.theme.navigation.main.MainScreen
import org.github.kudrix.famous.theme.AppTheme

@Composable
internal fun App() = AppTheme {
    FamousApp()
}

@Composable
internal fun FamousApp(
    navController: NavHostController = rememberNavController(),
){
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route ?: AppScreens.Login

    CompositionLocalProvider(
        LocalNavHost provides navController
    ){
        NavHost(
            navController = navController,
            startDestination = AppScreens.Login.title
        ){
            composable(route = AppScreens.Login.title){
                LoginScreen()
            }

            composable(route = AppScreens.Main.title){
                MainScreen()
            }

            composable(route = AppScreens.CreatePost.title){
                CreatePostScreen()
            }

            composable(route = AppScreens.Paywall.title){
                PaywallScreen()
            }
        }
    }
}


