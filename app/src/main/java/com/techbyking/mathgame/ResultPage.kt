package com.techbyking.mathgame

import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController


// David A. King  Dec 17, 2024
// Math Game  - Example game
// Udemy Jetpack Compose Class
// Oak Academy
//ResultPage.kt


@Composable
fun ResultPage(navController: NavController, score: Int?) {

    // get context for Toast messages
    val myContext = LocalContext.current as Activity

    val systemUIController = rememberSystemUiController()
    // change status bar color to blue
    systemUIController.setStatusBarColor(color = colorResource(R.color.ice_blue))

    Column (
        modifier = Modifier
            .fillMaxSize()
            // add picture background
            .paint(painter = painterResource(id = R.drawable.third),
                contentScale = ContentScale.FillBounds),
        horizontalAlignment = Alignment.CenterHorizontally
    )//end Column parameters
    {

        // spacer to drop the screen column down a bit
        Spacer(modifier = Modifier.height(50.dp))

        Text(text = "Congratulations", fontSize =  24.sp, color = Color.Red,
            fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(30.dp))

        Text(text = "Your Score: $score", fontSize =  24.sp, color = Color.Red,
            )//end score text

        Spacer(modifier = Modifier.height(100.dp))

        Row(modifier = Modifier
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
            ){

            //Play Again Button
            Button(
                onClick = {
                    navController.popBackStack(route = "FirstPage", inclusive = false)
                },
                //width and height of Button
                modifier = Modifier.size(150.dp, 60.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color
                    .White),
                shape = RoundedCornerShape(10.dp),
                border = _root_ide_package_.androidx.compose.foundation
                    .BorderStroke(2.dp, colorResource(id = R.color.blue))
            ){
                Text(text = "Play Again", fontSize = 20.sp, color = colorResource
                    (id = R.color.blue))
            }//end Button RowScope


            //Exit Button
            Button(
                onClick = {
                    // close the Activity
                    myContext.finish()
                },

                //width and height of Button
                modifier = Modifier.size(150.dp, 60.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color
                    .White),
                shape = RoundedCornerShape(10.dp),
                border = _root_ide_package_.androidx.compose.foundation
                    .BorderStroke(2.dp, colorResource(id = R.color.blue))
            ){
                Text(text = "Exit", fontSize = 20.sp, color = colorResource
                    (id = R.color.blue))
            }//end Button RowScope

        }



    }//end Column scope




}