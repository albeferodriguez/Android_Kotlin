package com.example.geoquiz

import android.content.Intent
import android.media.Image
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View.GONE
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*

class QuizActivity : AppCompatActivity() {

    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var question: TextView
    private lateinit var leftButton: ImageButton
    private lateinit var rightButton: ImageButton
    private lateinit var cheatButton: Button

    val provider: ViewModelProvider = ViewModelProvider(this)

    val quizViewModel: QuizViewModel by lazy {
        ViewModelProviders.of(this).get(QuizViewModel::class.java)
        provider.get(QuizViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("MainActivity", "Got a QuizViewModel: $quizViewModel")

        trueButton = findViewById<Button>(R.id.true_button)
        falseButton = findViewById<Button>(R.id.false_button)
        question = findViewById<TextView>(R.id.question)

        leftButton.setOnClickListener{
            getPrevQuestion()
        }

        trueButton.setOnClickListener {
            CheckAnswer(true)
        }

        falseButton.setOnClickListener {
            CheckAnswer(false)
        }

        rightButton.setOnClickListener {
            quizViewModel.currentIndex
            UpdateQuestion()
        }

        cheatButton.setOnClickListener{
            val answerIsTrue = quizViewModel.currentQuestionAnswer
            val intent = CheatActivity.newIntent(this, answerIsTrue)
            startActivity(intent)
        }
        UpdateQuestion()
    }

    private fun UpdateQuestion() {
    val questionResId = quizViewModel.currentQuestionText
        question.setText(questionResId)
        quizViewModel.increaseCurrentIndex()
    }

    fun getPrevQuestion(){
        if(quizViewModel.currentIndex - 1 >  0) {
            val questionResId = quizViewModel.currentQuestionText
            question.setText(questionResId)
        }
        quizViewModel.decreaseCurrentIndex()
    }

    fun CheckAnswer(userAnswer: Boolean) {

        val correctAnswer = quizViewModel.currentQuestionAnswer

        if (userAnswer == correctAnswer)
            Toast.makeText(this, "Correct", Toast.LENGTH_LONG).show()
        else
            Toast.makeText(this, "False", Toast.LENGTH_LONG).show()

    }
}
