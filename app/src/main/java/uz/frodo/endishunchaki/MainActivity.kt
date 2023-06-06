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

class MainActivity: AppCompatActivity(){
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var correct = 0
        var images = mutableListOf(apple, apricot, bananas, cherries, dates, figs, grape, lemon, peach, pineapple, apple, apricot, bananas, cherries, dates, figs, grape, lemon, peach, pineapple)
        var shImage  = mutableListOf<ShapeableImageView>(binding.img11,binding.img12,binding.img13,binding.img14, binding.img21,binding.img22,binding.img23,binding.img24, binding.img31,binding.img32,binding.img33,binding.img34, binding.img41,binding.img42,binding.img43,binding.img44, binding.img51,binding.img52,binding.img53,binding.img54,)
        var background = fruitsalad
        var anim1 = AnimationUtils.loadAnimation(this, R.anim.anim1)
        var anim2 = AnimationUtils.loadAnimation(this, R.anim.anim2)
        var count = 0
        var i2 = -1


        images.shuffle()
        for (i in 0..19){
            shImage[i].textAlignment = View.TEXT_ALIGNMENT_CENTER
            shImage[i].setOnClickListener {
                if (shImage[i].textAlignment == View.TEXT_ALIGNMENT_CENTER){
                    shImage[i].startAnimation(anim1)
                    shImage[i].setImageResource(images[i])
                    shImage[i].startAnimation(anim2)
                    shImage[i].textAlignment = View.TEXT_ALIGNMENT_GRAVITY
                    count++
                }else if (shImage[i].textAlignment == View.TEXT_ALIGNMENT_GRAVITY){
                    shImage[i].startAnimation(anim1)
                    shImage[i].setImageResource(background)
                    shImage[i].startAnimation(anim2)
                    shImage[i].textAlignment = View.TEXT_ALIGNMENT_CENTER
                    count--
                }

                Handler().postDelayed({
                    if (count == 2){
                        if (shImage[i].drawable.constantState == shImage[i2].drawable.constantState){
                            shImage[i].visibility = View.INVISIBLE
                            shImage[i2].visibility = View.INVISIBLE
                            correct++
                            count = 0
                        }else{
                            shImage[i].startAnimation(anim1)
                            shImage[i].setImageResource(background)
                            shImage[i].startAnimation(anim2)
                            shImage[i].textAlignment = View.TEXT_ALIGNMENT_CENTER

                            shImage[i2].startAnimation(anim1)
                            shImage[i2].setImageResource(background)
                            shImage[i2].startAnimation(anim2)
                            shImage[i2].textAlignment = View.TEXT_ALIGNMENT_CENTER
                            count = 0
                        }
                    }else if (count == 1){
                        i2 = i
                    }
                },500)

            }

        }

        object : CountDownTimer(61000,1000){
            override fun onTick(millisUntilFinished: Long) {
                binding.time.text = (millisUntilFinished/1000).toString()
            }

            override fun onFinish() {
                val i = Intent(applicationContext,MainActivity2::class.java)
                startActivity(i)
                finish()

            }

        }.start()
    }
}
/*class MainActivity : AppCompatActivity(),OnClickListener {
    private lateinit var binding: ActivityMainBinding
    lateinit var anim1:Animation
    lateinit var anim2:Animation
    var counter = 0
    var click1:ShapeableImageView? = null
    var click2:ShapeableImageView? = null
    var i11 = false; var i12 =false; var i13 =false; var i14 =false; var i21 = false; var i22 =false; var i23 =false; var i24 =false; var i31 = false; var i32 =false; var i33 =false; var i34 =false; var i41 = false; var i42 =false; var i43 =false; var i44 =false; var i51 = false; var i52 =false; var i53 =false; var i54 =false;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "Memory game"
        titleColor = Color.WHITE
        var correct = 0


        anim1 = AnimationUtils.loadAnimation(this,R.anim.anim1)
        anim2 = AnimationUtils.loadAnimation(this,R.anim.anim2)
        val anim3 = AnimationUtils.loadAnimation(this,R.anim.anim3)
        val anim4 = AnimationUtils.loadAnimation(this,R.anim.anim4)

        anim1.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationStart(animation: Animation?) {            }

            override fun onAnimationEnd(animation: Animation?) {
                when{
                    i11 ->{
                        binding.img11.setImageResource(R.drawable.apple)
                        binding.img11.startAnimation(anim2)
                        i11 = false
                    }
                    i12 ->{
                        binding.img12.setImageResource(R.drawable.apricot)
                        binding.img12.startAnimation(anim2)
                        i12 = false
                    }
                    i13 ->{
                        binding.img13.setImageResource(R.drawable.bananas)
                        binding.img13.startAnimation(anim2)
                        i13 = false
                    }
                    i14 ->{
                        binding.img14.setImageResource(R.drawable.cherries)
                        binding.img14.startAnimation(anim2)
                        i14 = false
                    }
                    i21 ->{
                        binding.img21.setImageResource(R.drawable.dates)
                        binding.img21.startAnimation(anim2)
                        i21 = false
                    }
                    i22 ->{
                        binding.img22.setImageResource(R.drawable.figs)
                        binding.img22.startAnimation(anim2)
                        i22 = false
                    }
                    i23 ->{
                        binding.img23.setImageResource(R.drawable.grape)
                        binding.img23.startAnimation(anim2)
                        i23 = false
                    }
                    i24 ->{
                        binding.img24.setImageResource(R.drawable.lemon)
                        binding.img24.startAnimation(anim2)
                        i24 = false
                    }
                    i31 ->{
                        binding.img31.setImageResource(R.drawable.peach)
                        binding.img31.startAnimation(anim2)
                        i31 = false
                    }
                    i32 ->{
                        binding.img32.setImageResource(R.drawable.pineapple)
                        binding.img32.startAnimation(anim2)
                        i32 = false
                    }
                    i33 ->{
                        binding.img33.setImageResource(R.drawable.apple)
                        binding.img33.startAnimation(anim2)
                        i33 = false
                    }
                    i34 ->{
                        binding.img34.setImageResource(R.drawable.apricot)
                        binding.img34.startAnimation(anim2)
                        i34 = false
                    }
                    i41 ->{
                        binding.img41.setImageResource(R.drawable.bananas)
                        binding.img41.startAnimation(anim2)
                        i41 = false
                    }
                    i42 ->{
                        binding.img42.setImageResource(R.drawable.cherries)
                        binding.img42.startAnimation(anim2)
                        i42 = false
                    }
                    i43 ->{
                        binding.img43.setImageResource(R.drawable.dates)
                        binding.img43.startAnimation(anim2)
                        i43 = false
                    }
                    i44 ->{
                        binding.img44.setImageResource(R.drawable.figs)
                        binding.img44.startAnimation(anim2)
                        i44 = false
                    }
                    i51 ->{
                        binding.img51.setImageResource(R.drawable.grape)
                        binding.img51.startAnimation(anim2)
                        i51 = false
                    }
                    i52 ->{
                        binding.img52.setImageResource(R.drawable.lemon)
                        binding.img52.startAnimation(anim2)
                        i52 = false
                    }
                    i53 ->{
                        binding.img53.setImageResource(R.drawable.peach)
                        binding.img53.startAnimation(anim2)
                        i53 = false
                    }
                    i54 ->{
                        binding.img54.setImageResource(R.drawable.pineapple)
                        binding.img54.startAnimation(anim2)
                        i54 = false
                    }
                }
            }

            override fun onAnimationRepeat(animation: Animation?) {}

        })

        anim2.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationStart(animation: Animation?) {            }

            override fun onAnimationEnd(animation: Animation?) {
                if (counter == 2){

                    if(click1?.drawable?.constantState == click2?.drawable?.constantState){
                        click1?.visibility = View.INVISIBLE
                        click2?.visibility = View.INVISIBLE
                        click1 = null
                        click2 = null
                        correct++
                        binding.correct.text = correct.toString()
                        if (correct == 10){
                            var i = Intent(applicationContext,MainActivity2::class.java)
                            i.putExtra("score",correct)
                            startActivity(i)
                            finish()
                        }
                    }else{
                        click1?.startAnimation(anim3)
                        click1?.setImageResource(R.drawable.fruitsalad)
                        click1?.startAnimation(anim4)
                        click2?.startAnimation(anim3)
                        click2?.setImageResource(R.drawable.fruitsalad)
                        click2?.startAnimation(anim4)
                        click1 = null
                        click2 = null
                    }
                    counter = 0
                }
            }

            override fun onAnimationRepeat(animation: Animation?) {}

        })


        val timer = object :CountDownTimer(61000,1000){
            override fun onTick(millisUntilFinished: Long) {
                binding.time.text = (millisUntilFinished/1000).toString()
            }

            override fun onFinish() {
                val intent = Intent(applicationContext, MainActivity2::class.java)
                intent.putExtra("score",correct)
                startActivity(intent)
                finish()
            }

        }
        timer.start()

        binding.img11.setOnClickListener(this)
        binding.img12.setOnClickListener(this)
        binding.img13.setOnClickListener(this)
        binding.img14.setOnClickListener(this)
        binding.img21.setOnClickListener(this)
        binding.img22.setOnClickListener(this)
        binding.img23.setOnClickListener(this)
        binding.img24.setOnClickListener(this)
        binding.img31.setOnClickListener(this)
        binding.img32.setOnClickListener(this)
        binding.img33.setOnClickListener(this)
        binding.img34.setOnClickListener(this)
        binding.img41.setOnClickListener(this)
        binding.img42.setOnClickListener(this)
        binding.img43.setOnClickListener(this)
        binding.img44.setOnClickListener(this)
        binding.img51.setOnClickListener(this)
        binding.img52.setOnClickListener(this)
        binding.img53.setOnClickListener(this)
        binding.img54.setOnClickListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = MenuInflater(this)
        menuInflater.inflate(R.menu.mymenu,menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.home ->{
                val i = Intent(applicationContext, MainActivity2::class.java)
                startActivity(i)
                finish()
            }
            R.id.exit ->{
                ActivityCompat.finishAffinity(this)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.img11 ->{
                if(click1 == null) click1 = binding.img11 else click2 = binding.img11
                counter++
                i11 = true
                binding.img11.startAnimation(anim1)
            }
            R.id.img12 ->{
                if(click1 == null) click1 = binding.img12 else click2 = binding.img12
                counter++
                i12 = true
                binding.img12.startAnimation(anim1)
            }
            R.id.img13 ->{
                if(click1 == null) click1 = binding.img13 else click2 = binding.img13
                counter++
                i13 = true
                binding.img13.startAnimation(anim1)
            }
            R.id.img14 ->{
                if(click1 == null) click1 = binding.img14 else click2 = binding.img14
                counter++
                i14 = true
                binding.img14.startAnimation(anim1)
            }
            R.id.img21 ->{
                if(click1 == null) click1 = binding.img21 else click2 = binding.img21
                counter++
                i21 = true
                binding.img21.startAnimation(anim1)
            }
            R.id.img22 ->{
                if(click1 == null) click1 = binding.img22 else click2 = binding.img22
                counter++
                i22 = true
                binding.img22.startAnimation(anim1)
            }
            R.id.img23 ->{
                if(click1 == null) click1 = binding.img23 else click2 = binding.img23
                counter++
                i23 = true
                binding.img23.startAnimation(anim1)
            }
            R.id.img24 ->{
                if(click1 == null) click1 = binding.img24 else click2 = binding.img24
                counter++
                i24 = true
                binding.img24.startAnimation(anim1)
            }
            R.id.img31 ->{
                if(click1 == null) click1 = binding.img31 else click2 = binding.img31
                counter++
                i31 = true
                binding.img31.startAnimation(anim1)
            }
            R.id.img32 ->{
                if(click1 == null) click1 = binding.img32 else click2 = binding.img32
                counter++
                i32 = true
                binding.img32.startAnimation(anim1)
            }
            R.id.img33 ->{
                if(click1 == null) click1 = binding.img33 else click2 = binding.img33
                counter++
                i33 = true
                binding.img33.startAnimation(anim1)
            }
            R.id.img34 ->{
                if(click1 == null) click1 = binding.img34 else click2 = binding.img34
                counter++
                i34 = true
                binding.img34.startAnimation(anim1)
            }
            R.id.img41 ->{
                if(click1 == null) click1 = binding.img41 else click2 = binding.img41
                counter++
                i41 = true
                binding.img41.startAnimation(anim1)
            }
            R.id.img42 ->{
                if(click1 == null) click1 = binding.img42 else click2 = binding.img42
                counter++
                i42 = true
                binding.img42.startAnimation(anim1)
            }
            R.id.img43 ->{
                if(click1 == null) click1 = binding.img43 else click2 = binding.img43
                counter++
                i43 = true
                binding.img43.startAnimation(anim1)
            }
            R.id.img44 ->{
                if(click1 == null) click1 = binding.img44 else click2 = binding.img44
                counter++
                i44 = true
                binding.img44.startAnimation(anim1)
            }
            R.id.img51 ->{
                if(click1 == null) click1 = binding.img51 else click2 = binding.img51
                counter++
                i51 = true
                binding.img51.startAnimation(anim1)
            }
            R.id.img52 ->{
                if(click1 == null) click1 = binding.img52 else click2 = binding.img52
                counter++
                i52 = true
                binding.img52.startAnimation(anim1)
            }
            R.id.img53 ->{
                if(click1 == null) click1 = binding.img53 else click2 = binding.img53
                counter++
                i53 = true
                binding.img53.startAnimation(anim1)
            }
            R.id.img54 ->{
                if(click1 == null) click1 = binding.img54 else click2 = binding.img54
                counter++
                i54 = true
                binding.img54.startAnimation(anim1)
            }
        }
    }

}*/