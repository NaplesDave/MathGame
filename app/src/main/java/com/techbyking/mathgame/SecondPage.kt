package com.techbyking.mathgame

import android.os.CountDownTimer
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import java.util.Locale


// David A. King  Dec 17, 2024
// Math Game  - Example game
// Udemy Jetpack Compose Class
// Oak Academy
//SecondPage.kt


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecondPage(navController : NavController, category : String) {

    // get context for Toast messages
    val myContext = LocalContext.current

    val systemUIController = rememberSystemUiController()
    // change status bar color to blue
    systemUIController.setStatusBarColor(color = colorResource(R.color.blue))

    //beginning Life Number
    val life = remember { mutableStateOf(3) }
    //beginning Score number
    val score = remember { mutableStateOf(0) }
    //beginning Time text
    val remainingTimeText = remember { mutableStateOf("30") }

    // text for question box on SecondPage
    val myQuestion = remember { mutableStateOf("") }

    // text for Answer box on SecondPage
    val myAnswer = remember { mutableStateOf("") }

    // SecondPage Button Enabled option
    val isEnabled = remember { mutableStateOf(true) }

    // returned Answer
    val correctAnswer = remember { mutableStateOf(0) }

    // total time for counter
    val totalTimeInMillis = remember {
        mutableStateOf(30000L) //30 seconds
    }

    //create countdown timer
    val timer = remember {
        mutableStateOf(
            object : CountDownTimer(totalTimeInMillis.value, 1000){
                override fun onTick(millisUntilFinished: Long) {
                    remainingTimeText.value = String.format(Locale
                        .getDefault(),
                        "%02d",
                        millisUntilFinished / 1000
                    )
                }

                override fun onFinish() {
                    cancel()
                    myQuestion.value = "Sorry, time is up!"
                    life.value -= 1 // reduce a life
                    isEnabled.value = false // disable OK button
                }

            }.start()
        )
    }

    //LaunchEffect -> enter the composition
    //SideEffect - > each re-composition
    //DisposableEffect -> leave the composition

    LaunchedEffect(key1 = "math", block ={

        val resultList = generateQuestion(category)

        myQuestion.value = resultList[0].toString()
        correctAnswer.value = resultList[1].toString().toInt()

        Log.d("question", myQuestion.value)

    } )


    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        //popBackStack returns to calling window
                        Icon( // add back arrow icon to appBar
                            Icons.AutoMirrored.Rounded.ArrowBack,
                            contentDescription = "back"
                        )//end Icon scope
                    }//end IconButton Scope
                },//end NavigationIcon scope

                title = {
                    Text(
                        text =
                        when (category) {
                            "add" -> "Addition"
                            "sub" -> "Subtraction"
                            "multi" -> "Multiplication"
                            else -> "Division"
                        }, fontSize = 20.sp//end when block
                    )//end Text scope
                },//end Title scope
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(id = R.color.blue),
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )//end Title scope
            )//end TopAppBr scope
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .paint(
                        painterResource(id = R.drawable.second),
                        contentScale = ContentScale.FillBounds
                    ),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {

                // add a space between TopAppBar and row
                Spacer(modifier = Modifier.height(20.dp))

                Row(modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                        // top scoreboard row
                    Text(text = "Life: ", fontSize = 16.sp, color = Color.White)
                    Text(text = life.value.toString(), fontSize = 16.sp, color = Color
                        .White)
                    Text(text = "Score: ", fontSize = 16.sp, color = Color
                        .White)
                    Text(text = score.value.toString(), fontSize = 16.sp, color = Color
                        .White)
                    Text(text = "Remaining Time: ", fontSize = 16.sp, color =
                    Color.White)
                    Text(text = remainingTimeText.value, fontSize = 16.sp,
                        color = Color.White)

                }// End Row scope

                Spacer(modifier = Modifier.height(30.dp))

                TextForQuestion(text = myQuestion.value)

                Spacer(modifier = Modifier.height(15.dp))

                TextFieldForAnswer(text = myAnswer)

                Spacer(modifier = Modifier.height(50.dp))

                //add a Row for the Buttons

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment =  Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ){
                    ButtonOkNext(
                        buttonText = "OK",
                        myOnClick = {
                            isEnabled.value = false
                            if (myAnswer.value.isEmpty()){
                                Toast.makeText(myContext, "Write an answer or" +
                                        " click the Next button", Toast
                                            .LENGTH_SHORT).show()
                            }else {

                                //stop/cancel timer
                                timer.value.cancel()

                                isEnabled.value = false
                                //check answer
                                // Correct add 10 points to score
                                if (myAnswer.value.toInt() == correctAnswer
                                    .value){

                                    score.value += 10
                                    myQuestion.value = "Congratulations..."
                                    myAnswer.value = ""

                                }else{ // wrong answer

                                    life.value -= 1 // take away a life
                                    myQuestion.value = "Sorry, your answer is" +
                                            " wrong."
                                }//end inner else block

                            }//end outer else block

                        },
                        isEnabled = isEnabled.value
                    )

                    ButtonOkNext(
                        buttonText = "NEXT",
                        myOnClick = {

                            // cancel the timer
                            timer.value.cancel()
                            // start timer fresh
                            timer.value.start()

                            // check if lives left
                            if (life.value == 0){

                                Toast.makeText(myContext, "Game Over"
                                        , Toast
                                    .LENGTH_SHORT).show()

                                //Open the result Page, pass the score in
                                navController.navigate("ResultPage/${score
                                    .value}"){

                                    // remove second page from stack.
                                    //leave FirstPage to popBackto but add
                                    //ResultsPage on top of stack
                                    popUpTo("FirstPage"){inclusive=false}
                                }

                            }else{

                                val newResultList = generateQuestion(category)
                                myQuestion.value = newResultList[0].toString()
                                correctAnswer.value = newResultList[1]
                                    .toString().toInt()
                                // erase previous answer from screen
                                myAnswer.value = ""
                                isEnabled.value = true
                            }
                        },
                        isEnabled = true
                    )
                }

            }//end column scope
        }//end content scope

    )//end Scaffold scope

}//end SecondPage fun scope

