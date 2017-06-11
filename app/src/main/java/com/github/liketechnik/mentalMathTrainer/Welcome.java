package com.github.liketechnik.mentalMathTrainer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.github.liketechnik.mentalmathtrainer.R;

public class Welcome extends AppCompatActivity {

    static public String default_text_size = "18sp";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    /** Called when the user clicks the Let's go! Button */
    public void newTrainingSession(View view) {
        Intent intent = new Intent(this, NewTrainingSession.class);
        startActivity(intent);
    }
}
