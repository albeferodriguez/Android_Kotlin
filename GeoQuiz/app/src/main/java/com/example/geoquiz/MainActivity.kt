package com.example.geoquiz

import android.media.Image
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var question: TextView
    private lateinit var leftButton: ImageButton
    private lateinit var rightButton: ImageButton

    private val questionList = listOf(
        Question(R.string.question_Australia, true),
        Question(R.string.question_Franica, false),
        Question(R.string.question_Norway, true),
        Question(R.string.question_Russia, true),
        Question(R.string.question_Spain, false)
    )

    private var currentIndex = 0
    private var prevIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
            currentIndex = (currentIndex + 1) % questionList.size
            UpdateQuestion()
        }
        UpdateQuestion()
    }

    fun UpdateQuestion() {
        val questionTextResId = questionList[currentIndex].question
        question.setText(questionTextResId)
        currentIndex++
    }

    fun getPrevQuestion(){
        if(currentIndex - 1 >  0) {
            val questionResId = questionList[currentIndex - 1].question
            question.setText(questionResId)
        }
        currentIndex -= 1
    }

    fun CheckAnswer(userAnswer: Boolean) {

        val correctAnswer = questionList[currentIndex].correct

        if (userAnswer == correctAnswer)
            Toast.makeText(this, "Correct", Toast.LENGTH_LONG).show()
        else
            Toast.makeText(this, "False", Toast.LENGTH_LONG).show()

    }
}
