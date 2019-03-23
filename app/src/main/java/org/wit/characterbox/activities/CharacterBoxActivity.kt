    package org.wit.characterbox.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import org.wit.characterbox.R
import org.wit.characterbox.main.MainApp
import org.wit.characterbox.models.CharacterBoxModel

    class CharacterBoxActivity : AppCompatActivity(), AnkoLogger {

        var characterbox = CharacterBoxModel()
        lateinit var app : MainApp



        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
            app = application as MainApp

        btnAdd.setOnClickListener() {
            val characterTitle = characterTitle.text.toString()
            characterbox.aName = actorTitle.text.toString()
            if (characterTitle.isNotEmpty()) {
                app.characterboxes.add(characterbox.copy())
                info("add Button Pressed: $characterTitle")
                app.characterboxes.forEach { info("add Button Pressed: ${it}")}
                setResult(AppCompatActivity.RESULT_OK)
                finish()
            }
            else {
                toast ("Please Enter a name")
            }
        }
    }
}
