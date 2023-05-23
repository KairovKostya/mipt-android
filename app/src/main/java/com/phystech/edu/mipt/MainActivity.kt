package com.phystech.edu.mipt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.phystech.edu.mipt.screen.signUp.SignUpScreen
import com.phystech.edu.mipt.screen.signUp.SignUpScreenViewModel
import com.phystech.edu.mipt.screen.mainMenu.MainMenuScreen
import com.phystech.edu.mipt.screen.mainMenu.MainMenuViewModel
import com.phystech.edu.mipt.ui.theme.MiptTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            MiptTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    NavHost(navController = navController, startDestination = "sign_up") {
                        composable("sign_up") {
                            val signUpViewModel = hiltViewModel<SignUpScreenViewModel>()
                            SignUpScreen(
                                signUpScreenViewModel = signUpViewModel,
                                navController = navController
                            )
                        }
                        composable("main_menu") {
                            val restaurantViewModel = hiltViewModel<MainMenuViewModel>()
                            MainMenuScreen(
                                restaurantViewModel = restaurantViewModel,
                                navController = navController
                            )
                        }
                    }
                }
            }
        }
    }
}

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        MiptTheme {
        }
    }