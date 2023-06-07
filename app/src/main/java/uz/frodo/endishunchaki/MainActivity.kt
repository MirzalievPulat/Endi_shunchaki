package uz.frodo.endishunchaki

import android.app.Activity
import android.app.Instrumentation.ActivityResult
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.GradientDrawable.Orientation
import android.icu.lang.UCharacter.VerticalOrientation
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import uz.frodo.endishunchaki.R.drawable.*
import android.os.CountDownTimer
import android.os.Handler
import android.view.Gravity
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.View.OnClickListener
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.app.ActivityCompat
import com.google.android.material.imageview.ShapeableImageView
import uz.frodo.endishunchaki.databinding.ActivityMainBinding
import java.util.Timer
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var correct = 0
        var images = mutableListOf(
            apple,
            apricot,
            bananas,
            cherries,
            dates,
            figs,
            grape,
            lemon,
            peach,
            pineapple,
            apple,
            apricot,
            bananas,
            cherries,
            dates,
            figs,
            grape,
            lemon,
            peach,
            pineapple
        )
        var shImage = mutableListOf<ShapeableImageView>(
            binding.img11,
            binding.img12,
            binding.img13,
            binding.img14,
            binding.img21,
            binding.img22,
            binding.img23,
            binding.img24,
            binding.img31,
            binding.img32,
            binding.img33,
            binding.img34,
            binding.img41,
            binding.img42,
            binding.img43,
            binding.img44,
            binding.img51,
            binding.img52,
            binding.img53,
            binding.img54,
        )
        var background = fruitsalad
        var anim1 = AnimationUtils.loadAnimation(this, R.anim.anim1)
        var anim2 = AnimationUtils.loadAnimation(this, R.anim.anim2)
        var count = 0
        var i2 = -1
        var turnOver = true


        images.shuffle()
        for (i in 0..19) {
            shImage[i].textAlignment = View.TEXT_ALIGNMENT_CENTER
            shImage[i].setOnClickListener {
                if (shImage[i].textAlignment == View.TEXT_ALIGNMENT_CENTER && turnOver) {
                    shImage[i].startAnimation(anim1)
                    shImage[i].setImageResource(images[i])
                    shImage[i].startAnimation(anim2)
                    shImage[i].textAlignment = View.TEXT_ALIGNMENT_GRAVITY
                    count++
                } else if (shImage[i].textAlignment == View.TEXT_ALIGNMENT_GRAVITY) {
                    shImage[i].startAnimation(anim1)
                    shImage[i].setImageResource(background)
                    shImage[i].startAnimation(anim2)
                    shImage[i].textAlignment = View.TEXT_ALIGNMENT_CENTER
                    count--
                }
                if (count == 2) {
                    turnOver = false
                    if (shImage[i].drawable.constantState == shImage[i2].drawable.constantState) {
                        shImage[i].visibility = View.INVISIBLE
                        shImage[i2].visibility = View.INVISIBLE
                        correct++
                        binding.correct.text = correct.toString()
                        if (correct == 10){
                            val intent = Intent(applicationContext, MainActivity2::class.java)
                            startActivity(intent)
                            finish()
                        }
                        count = 0
                        turnOver = true
                    } else {
                        Handler().postDelayed({shImage[i].startAnimation(anim1)
                            shImage[i].setImageResource(background)
                            shImage[i].startAnimation(anim2)
                            shImage[i].textAlignment = View.TEXT_ALIGNMENT_CENTER

                            shImage[i2].startAnimation(anim1)
                            shImage[i2].setImageResource(background)
                            shImage[i2].startAnimation(anim2)
                            shImage[i2].textAlignment = View.TEXT_ALIGNMENT_CENTER

                            turnOver = true
                            count = 0},150)

                    }
                } else if (count == 1) {
                    i2 = i
                }

            }

        }

        object : CountDownTimer(61000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                binding.time.text = (millisUntilFinished / 1000).toString()
            }

            override fun onFinish() {
                val i = Intent(applicationContext, MainActivity2::class.java)
                startActivity(i)
                finish()

            }

        }.start()
    }
}
