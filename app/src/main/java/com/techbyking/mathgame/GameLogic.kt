package com.techbyking.mathgame


// David A. King  Dec 17, 2024
// Math Game  - Example game
// Udemy Jetpack Compose Class
// Oak Academy
//GameLogic.kt


import kotlin.random.Random

fun generateQuestion(selectedCategory : String) : ArrayList<Any> {

    var number1 = Random.nextInt(0, 100)
    var number2 = Random.nextInt(0, 100)

    val textQuestion : String
    val correctAnswer : Int

    when(selectedCategory){

        "add" -> {
            textQuestion = "$number1 + $number2"
            correctAnswer = number1 + number2
        }//end add

        "sub" -> {
            if (number1 >= number2){
                textQuestion = "$number1 - $number2"
                correctAnswer  = number1 - number2
            }else {
                textQuestion = "$number2 - $number1"
                correctAnswer = number2 - number1
            }
        }//end sub

        "multi" -> {
            number1 = Random.nextInt(0,16)
            number2 = Random.nextInt(0, 16)

            textQuestion = "$number1 * $number2"
            correctAnswer  = number1 * number2
        }//end multi

        else -> {
            if (number1 == 0 || number2 == 0){

                textQuestion = " 0 / 1"
                correctAnswer = 0 // 15 % 7 = 1 --> 15 - 1 = 14 --> / 7 = 2
            }else if (number1 >= number2) {

                // remove possible remainder on next line
                val newBigNumber = number1 - (number1 % number2)

                textQuestion = "$newBigNumber  / $number2"
                correctAnswer = newBigNumber / number2
            }else {

                // remove possible remainder on next line
                val newBigNumber = number2 - (number2 % number1)

                textQuestion = "$newBigNumber / $number1"
                correctAnswer = newBigNumber / number1
            }//end final else

        }//end outer else

    }//end when block

    // put answers into an Array to return as One object
    val gameResultList = ArrayList<Any>()
    gameResultList.add(textQuestion)
    gameResultList.add(correctAnswer)

    return gameResultList

}//end GenerateQuestion Fun