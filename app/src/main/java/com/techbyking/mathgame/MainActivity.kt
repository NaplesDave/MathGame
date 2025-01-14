package com.techbyking.mathgame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.techbyking.mathgame.ui.theme.MathGameTheme


// David A. King  Dec 17, 2024
// Math Game  - Example game
// Udemy Jetpack Compose Class
// Oak Academy
//MainActivity.kt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MathGameTheme {
                Surface (
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyNavigation()
                }//end Surface activity call scope
            }//end Theme scope
        }//end setContent scope
    }//end onCreate Scope
}//end MainActivity fun

@Composable
fun MyNavigation(){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "FirstPage"){

        composable(route = "FirstPage")
        {
            FirstPage(navController = navController)
        }

        // get the page, and the argument,
        // passed to the navController for
        // calling Playing page
        composable(route = "SecondPage/{category}",
        arguments = listOf(
            navArgument("category"){type = NavType.StringType }
        )//end listOf block

        ){
            val selectedCategory = it.arguments?.getString("category")

            selectedCategory?.let{ category ->
                //call the second page, passing the category to function
                SecondPage(navController = navController, category = category)
            }//end let scope

        }


        // get the page, and the argument,
        // passed to the navController for
        // calling Result page
        composable(route = "ResultPage/{score}",
            arguments = listOf(
                navArgument("score"){type = NavType.IntType }
            )//end listOf block

        ){
            val userScore = it.arguments?.getInt("score")

            userScore.let{ score ->
                //call the second page, passing the category to function
                ResultPage(navController = navController, score = score)
            }//end let scope

        }

    }//end startDestination route scope

}// end MyNavigation fun
