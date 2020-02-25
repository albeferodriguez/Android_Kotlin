package com.example.geoquiz

import android.app.Activity
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

private const val EXTRA_ANSWER_SHOWN = "com.example.geoquiz.answer_shown"
private const val REQUEST_CODE_CHEAT = 0


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
            startActivityForResult(intent, REQUEST_CODE_CHEAT)
        }
        UpdateQuestion()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode != Activity.RESULT_OK) return

        if(requestCode == REQUEST_CODE_CHEAT){
            quizViewModel.isCheater = data?.getBooleanExtra(EXTRA_ANSWER_SHOWN, false) ?: false
        }
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

        val checkAnswerResId = when{
            quizViewModel.isCheater -> R.string.judgment_toast
            userAnswer == correctAnswer -> R.string.correct_toast
            else -> R.string.incorrect_toast
        }

        if (userAnswer == correctAnswer)
            Toast.makeText(this, "Correct", Toast.LENGTH_LONG).show()
        else
            Toast.makeText(this, "False", Toast.LENGTH_LONG).show()

    }
}
