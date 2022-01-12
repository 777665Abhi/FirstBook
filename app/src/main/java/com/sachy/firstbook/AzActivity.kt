package com.sachy.firstbook

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech;
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import com.sachy.firstbook.databinding.ActivityMainBinding
import java.util.*
import kotlin.collections.ArrayList


class AzActivity : AppCompatActivity(), View.OnClickListener {

    var characterPosition: Int = 0
    private var speakFlag: Boolean = false
    lateinit var binding: ActivityMainBinding
    lateinit var anim:Animation

    lateinit var tts: TextToSpeech
    private var characterArrays = ArrayList<String>()
    private var type:Int=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        init()
    }

    /**Screen initialize*/
    private fun init() {
        type=intent.extras!!.getInt("type",0)

        //Set Data
        when(type)
        {
            0-> characterArrays = ArrayList(listOf("A", "B", "C", "D", "E", "F", "G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"))
      1-> characterArrays = ArrayList(listOf("1","2","3","4","5","6","7","8","9","10"))
      2->characterArrays = ArrayList(listOf("1","2"))
      3->characterArrays = ArrayList(listOf("1","2"))
       }

        //Initialize tts
        tts = TextToSpeech(applicationContext) { status ->
            if (status != TextToSpeech.ERROR) {
                tts.language = Locale.UK
                tts.setSpeechRate(0.5f)
                speakFlag = true
            }
        }

        //Click listener reg
        binding.tvCharacter.setOnClickListener(this)
        binding.btNext.setOnClickListener(this)
        binding.btPrevious.setOnClickListener(this)

        //Setting animation
        anim = AnimationUtils.loadAnimation(
            applicationContext,
            R.anim.anim_rotate);

        binding.tvCharacter.text=characterArrays[0]
    }

    private fun speak(text: String) {
//        if (speakFlag)
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            tts.speak(text, TextToSpeech.QUEUE_ADD, null, null);
//        } else {
//            tts.speak(text, TextToSpeech.QUEUE_ADD, null);
//        }
    }

    override fun onClick(view: View?) {
        when (view) {
            binding.tvCharacter -> {
                speak(characterArrays[characterPosition])
            }
            binding.btNext -> {
                characterPosition = ++characterPosition
                if (characterPosition < characterArrays.size) {
                    binding.tvCharacter.text = characterArrays[characterPosition]
                    binding.tvCharacter.startAnimation(anim)

                } else {
                    characterPosition = --characterPosition
                }
            }
            binding.btPrevious -> {
                characterPosition = --characterPosition
                if (characterPosition < 0) {
                    characterPosition = ++characterPosition
                } else {
                    binding.tvCharacter.text = characterArrays[characterPosition]
                    binding.tvCharacter.startAnimation(anim)
                }
            }

        }
    }
}