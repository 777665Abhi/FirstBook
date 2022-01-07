package com.sachy.firstbook

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech;
import android.view.View
import androidx.databinding.DataBindingUtil
import com.sachy.firstbook.databinding.ActivityMainBinding
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity(), View.OnClickListener {

    var characterPosition: Int = 0
    private val characterArrays = ArrayList<String>(listOf("A", "B", "C", "D", "E", "F", "G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"))
    private var speakFlag: Boolean = false
    lateinit var binding: ActivityMainBinding

    lateinit var tts: TextToSpeech
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        init()
    }

    /**Screen initialize*/
    private fun init() {
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
                }
            }

        }
    }
}