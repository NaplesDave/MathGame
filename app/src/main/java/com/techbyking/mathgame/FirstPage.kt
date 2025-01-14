package com.techbyking.mathgame

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController


// David A. King  Dec 17, 2024
// Math Game  - Example game
// Udemy Jetpack Compose Class
// Oak Academy
//FirstPage.kt


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FirstPage(navController: NavController) {

    val systemUIController = rememberSystemUiController()
    systemUIController.setStatusBarColor(color = colorResource(R.color.green))

    Scaffold(
        topBar = { // First parameter
            TopAppBar(// design TopAppBar
                title = { Text("Math Game", fontSize = 20.sp) },
                colors = topAppBarColors(
                    containerColor = colorResource(R.color.green),
                    titleContentColor = Color.White
                )

            )//end TopAppBar design
        }, // end topBar parameter scope
        content = {// Second parameter column with Buttons
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .paint(painter = painterResource(R.drawable.first),
                        contentScale = ContentScale.FillBounds),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            )//end Column parameter scope
            {// begin column design scope

                Button(
                    onClick = {
                        navController.navigate("SecondPage/add")
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.green)
                    ),
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier.size(250.dp,100.dp)
                ) {
                    Text(
                        text = "Addition",
                        color = Color.White,
                        fontSize = 24.sp
                    )
                }

                Button(
                    onClick = {
                        navController.navigate("SecondPage/sub")
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.green)
                    ),
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier.size(250.dp,100.dp)
                ) {
                    Text(
                        text = "Subtraction",
                        color = Color.White,
                        fontSize = 24.sp
                    )
                }

                Button(
                    onClick = {
                        navController.navigate("SecondPage/multi")
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.green)
                    ),
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier.size(250.dp,100.dp)
                ) {
                    Text(
                        text = "Multiplication",
                        color = Color.White,
                        fontSize = 24.sp
                    )
                }

                Button(//begin Button parameters
                    onClick = {
                        navController.navigate("SecondPage/div")
                    },//end onClick action
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.green)
                    ),
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier.size(250.dp,100.dp)
                )  //end button parameters scope
                // add Text to Button in a Row
                {// begin Row scope
                    Text(
                        text = "Division",
                        color = Color.White,
                        fontSize = 24.sp
                    )//end Text scope
                }//end Row scope

            } // end column scope
        }// end content scope
    )// end Scaffold scope

}//end function FirstPage scope