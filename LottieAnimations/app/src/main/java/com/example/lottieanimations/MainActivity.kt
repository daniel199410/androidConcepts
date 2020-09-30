package com.example.lottieanimations

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener{
            animation_view.playAnimation()
        }
        goButton.setOnClickListener{
            val intent = Intent(this, View2::class.java)
            startActivity(intent)
        }
    }
}