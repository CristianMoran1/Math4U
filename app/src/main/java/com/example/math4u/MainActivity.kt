package com.example.math4u

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import kotlinx.coroutines.NonCancellable.start
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    var timeTextView: TextView? = null
    var scoreTextView:TextView? = null
    var questionTextView:TextView? = null
    var alertTextView:TextView? = null
    var finalScoreTextView:TextView? = null
    var btn0: Button? = null
    var btn1: Button? = null
    var btn2: Button? = null
    var btn3: Button? = null

    var countDownTimer: CountDownTimer? = null
    var random: Random = Random
    var a = 0
    var b = 0
    var indexOfCorrectAnswer = 0
    var answer = ArrayList<Int>()
    var points = 0
    var totalQuestions = 0
    var cals = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val calInt = intent.getStringExtra("cals")
        cals = calInt!!
        timeTextView = findViewById(R.id.TimeTextView)
        questionTextView = findViewById(R.id.QuestionTextText)
        scoreTextView = findViewById(R.id.ScoreTextView)
        alertTextView = findViewById(R.id.AlertTextView)

        btn0= findViewById(R.id.button0)
        btn1= findViewById(R.id.button1)
        btn2 = findViewById(R.id.button2)
        btn3 = findViewById(R.id.button3)

        start()

    }
    fun NextQuestion(cal:String) {
        a = random.nextInt(10)
        b = random.nextInt(10)
        questionTextView!!.text = "$a $cal $b"
        indexOfCorrectAnswer = random.nextInt(4)

        answer.clear()

        for (i in 0..3) {
            if (indexOfCorrectAnswer == i) {
                when (cal) {
                    "+" -> { answer.add(a + b) }
                    "-" -> {answer.add(a - b) }
                    "*" -> { answer.add(a * b) }
                    "/" -> {
                        try {
                            answer.add(a / b)
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }

                    }
                }
            }
            else {
                var wrongAnswer = random.nextInt(20)
                try {
                    while (wrongAnswer == a + b ||
                        wrongAnswer == a - b ||
                        wrongAnswer == a * b ||
                        wrongAnswer == a / b
                    ) {
                        wrongAnswer = random.nextInt(20)
                    }
                    answer.add(wrongAnswer)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                try {
                    btn0!!.text ="answers[0]"
                    btn1!!.text ="answers[1]"
                    btn2!!.text ="answers[2]"
                    btn3!!.text ="answers[3]"
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    fun optionSelect(view: View?) {
        totalQuestions++
        if (indexOfCorrectAnswer.toString() == view!!.tag.toString()) {
            points++
            alertTextView!!.text = "Correct"
        } else {
            alertTextView!!.text = "Wrong"
        }
        scoreTextView!!.text = "$points/$totalQuestions"
        NextQuestion(cals)
    }
    fun PlayAgain(view:View){
        points =0
        totalQuestions=0
        scoreTextView!!.text="$points/$totalQuestions"
        countDownTimer!!.start()
    }
    private fun start(){
        NextQuestion(cals)
        countDownTimer = object: CountDownTimer(3000, 1000){
            override fun onTick(p0: Long) {
                timeTextView!!.text = (p0/1000).toString() + "s"
            }

            override fun onFinish() {
                timeTextView!!.text = "Times up"
                openDialog()
            }
        }.start()
    }

    private fun openDialog() {
        val inflate = LayoutInflater.from(this)
        val winDialog = inflate.inflate(R.layout.win_layout, null)
        finalScoreTextView = winDialog.findViewById(R.id.FinalScoreTextView)
        val btnPlayAgain = winDialog.findViewById<Button>(R.id.buttonPlayAgain)
        val dialog = AlertDialog.Builder(this)
        dialog.setCancelable(false)
        dialog.setView(winDialog)
        val showDialog = dialog.create()
    }


}