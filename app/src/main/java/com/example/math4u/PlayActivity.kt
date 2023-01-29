package com.example.math4u

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class PlayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)

        findViewById<ImageView>(R.id.multi).setOnClickListener{
            val calInt = Intent(this@PlayActivity, MainActivity::class.java)

            calInt.putExtra("cals", "*")
            startActivity(calInt)
        }
        findViewById<ImageView>(R.id.add).setOnClickListener{
            val calInt = Intent(this@PlayActivity, MainActivity::class.java)

            calInt.putExtra("cals", "+")
            startActivity(calInt)
        }
        findViewById<ImageView>(R.id.div).setOnClickListener{
            val calInt = Intent(this@PlayActivity, MainActivity::class.java)

            calInt.putExtra("cals", "/")
            startActivity(calInt)
        }
        findViewById<ImageView>(R.id.sub).setOnClickListener {
            val calInt = Intent(this@PlayActivity, MainActivity::class.java)

            calInt.putExtra("cals", "-")
            startActivity(calInt)
        }
    }
}