package com.rsschool.quiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.lang.StringBuilder

class MainActivity : AppCompatActivity(), Launcher {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        launchQuestionFragment(0)
    }

    override fun launchQuestionFragment(questionNumber: Int) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.container, QuizFragment.newInstance(questionNumber))
            commit()
        }
    }

    override fun launchResultFragment() {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.container, ResultFragment())
            commit()
        }
    }

    override fun shareResult(counting: Int) {
        val message = StringBuilder("").apply {
            append("Your result: $counting из 100 \n\n")
            for (i in 0..4) {
                append("${QuestionBase.questions[i].textQuestion}\n\n")
                append("${QuestionBase.questions[i].answers[QuestionBase.questions[i].userAnswer].first}\n\n")
            }
        }
        val intent: Intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, "Quiz results")
            putExtra(Intent.EXTRA_TEXT, "$message")
        }
        startActivity(intent)
    }


}

interface Launcher {
    fun launchQuestionFragment(questionNumber: Int)
    fun launchResultFragment()
    fun shareResult(counting: Int)
}