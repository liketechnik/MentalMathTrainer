package com.github.liketechnik.mentalMathTrainer

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.github.liketechnik.mentalMathTrainer.R

class Welcome : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
    }

    /** Called when the user clicks the Let's go! Button  */
    fun newTrainingSession(view: View) {
        val intent = Intent(this, NewTrainingSession::class.java)
        startActivity(intent)
    }

    companion object {
        var default_text_size = "18sp"
    }
}
