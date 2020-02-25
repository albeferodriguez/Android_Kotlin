package com.example.geoquiz

import android.util.Log
import androidx.lifecycle.ViewModel

private const val TAG =" QuizViewModel"

class QuizViewModel: ViewModel() {

    init {
        Log.d(TAG, "ViewModel instance created")
    }
    val questionList = listOf(
        Question(R.string.question_Australia, true),
        Question(R.string.question_Franica, false),
        Question(R.string.question_Norway, true),
        Question(R.string.question_Russia, true),
        Question(R.string.question_Spain, false)
    )

    var currentIndex = 0

    val currentQuestionAnswer: Boolean
        get() = questionList[currentIndex].correct

    val currentQuestionText:Int
        get() = questionList[currentIndex].question

    fun moveToNext(){
        currentIndex = (currentIndex + 1) % questionList.size
    }

    fun decreaseCurrentIndex() = currentIndex - 1
    fun increaseCurrentIndex() = currentIndex + 1

    override fun onCleared() {
        super.onCleared()

        Log.d(TAG, "ViewModel intance about to be destroyed")
    }
}