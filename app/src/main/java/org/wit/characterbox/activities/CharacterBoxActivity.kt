    package org.wit.characterbox.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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

            toolbarAdd.title = title
            setSupportActionBar(toolbarAdd)

            if (intent.hasExtra("characterbox_edit")) {
                characterbox = intent.extras.getParcelable<CharacterBoxModel>("characterbox_edit")
                characterTitle.setText(characterbox.cName)
                actorTitle.setText(characterbox.aName)
            }

        btnAdd.setOnClickListener() {
            val characterTitle = characterTitle.text.toString()
            characterbox.aName = actorTitle.text.toString()
            if (characterTitle.isNotEmpty()) {
                app.characterboxes.create(characterbox.copy())
                info("add Button Pressed: $characterTitle")
                setResult(AppCompatActivity.RESULT_OK)
                finish()
            }
            else {
                toast ("Please Enter a name")
            }
        }
    }

        override fun onCreateOptionsMenu(menu: Menu?): Boolean {
            menuInflater.inflate(R.menu.menu_characterbox, menu)
            return super.onCreateOptionsMenu(menu)
        }

        override fun onOptionsItemSelected(item: MenuItem?): Boolean {
            when (item?.itemId) {
                R.id.item_cancel -> {
                    finish()
                }
            }
            return super.onOptionsItemSelected(item)
        }
}
