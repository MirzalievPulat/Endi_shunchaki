package uz.frodo.endishunchaki

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity2 : AppCompatActivity() {
    private lateinit var lastScore:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        lastScore = findViewById<TextView>(R.id.lastScore)


        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            val i = Intent(this,MainActivity::class.java)
            startActivity(i)
        }



    }

}