    package org.wit.characterbox.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import org.wit.characterbox.R
import org.wit.characterbox.models.CharacterBoxModel

    class CharacterBoxActivity : AppCompatActivity(), AnkoLogger {

        var characterbox = CharacterBoxModel()
        val characterboxes = ArrayList<CharacterBoxModel>()


        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAdd.setOnClickListener() {
            val characterTitle = characterTitle.text.toString()
            characterbox.actorName = actorTitle.text.toString()
            if (characterTitle.isNotEmpty()) {
                characterboxes.add(characterbox.copy())
                characterboxes.forEach { info("add Button Pressed: ${it.cName}")}
                characterboxes.forEach { info("add Button Pressed: ${it.actorName}")}
            }
            else {
                toast ("Please Enter a name")
            }
        }
    }
}
