package com.tsvigun.workmatestarwars.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.tsvigun.workmatestarwars.presentation.detail.DetailScreen
import com.tsvigun.workmatestarwars.presentation.main.MainScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {

                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = "main") {

                        composable("main") {
                            MainScreen(
                                onCharacterClick = { characterId ->
                                    navController.navigate("detail/$characterId")
                                }
                            )
                        }

                        composable(
                            route = "detail/{characterId}",
                            arguments = listOf(navArgument("characterId") {
                                type = NavType.StringType
                            })
                        ) { backStackEntry ->
                            val id = backStackEntry.arguments?.getString("characterId") ?: ""

                            DetailScreen(
                                characterId = id,
                                onBackClick = {
                                    navController.popBackStack()
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}