    package org.wit.characterbox.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.card_characterbox.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import org.wit.characterbox.R
import org.wit.characterbox.main.MainApp
import org.wit.characterbox.models.CharacterBoxModel
import org.wit.characterbox.org.wit.characterbox.helpers.readImage
import org.wit.characterbox.org.wit.characterbox.helpers.readImageFromPath
import org.wit.characterbox.org.wit.characterbox.helpers.showImagePicker

    class CharacterBoxActivity : AppCompatActivity(), AnkoLogger {

        var characterbox = CharacterBoxModel()
        lateinit var app: MainApp
        val IMAGE_REQUEST = 1
        var edit = false;


        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
            app = application as MainApp

            toolbarAdd.title = title
            setSupportActionBar(toolbarAdd)



            if (intent.hasExtra("characterbox_edit")) {
                edit = true
                characterbox = intent.extras.getParcelable<CharacterBoxModel>("characterbox_edit")
                characterTitle.setText(characterbox.cName)
                actorTitle.setText(characterbox.aName)
                btnAdd.setText(R.string.save_characterbox)
                characterImage.setImageBitmap(readImageFromPath(this, characterbox.image))
                if (characterbox.image != null) {
                    chooseImage.setText(R.string.change_character_image)
                }
            }

            btnAdd.setOnClickListener() {
                val characterTitle = characterTitle.text.toString()
                characterbox.cName = characterTitle
                characterbox.aName = actorTitle.text.toString()

                if (characterbox.cName.isEmpty()) {
                    toast(R.string.enter_characterbox_title)
                } else {
                    if (edit) {
                        app.characterboxes.update(characterbox.copy())
                    } else {
                        app.characterboxes.create(characterbox.copy())
                    }
                }
                info("add Button Pressed: $characterTitle")
                setResult(AppCompatActivity.RESULT_OK)
                finish()
            }

            chooseImage.setOnClickListener {
                showImagePicker(this, IMAGE_REQUEST)
            }


        }


        override fun onCreateOptionsMenu(menu: Menu?): Boolean {
            menuInflater.inflate(R.menu.menu_characterbox, menu)
            if (edit && menu != null) menu.getItem(0).setVisible(true)
            return super.onCreateOptionsMenu(menu)
        }

        override fun onOptionsItemSelected(item: MenuItem?): Boolean {
            when (item?.itemId) {
                R.id.item_delete -> {
                    app.characterboxes.delete(characterbox)
                    finish()
                }
                R.id.item_cancel -> {
                    finish()
                }
            }
            return super.onOptionsItemSelected(item)
        }

        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)
            when (requestCode) {
                IMAGE_REQUEST -> {
                    if (data != null) {
                        characterbox.image = data.getData().toString()
                        characterImage.setImageBitmap(readImage(this, resultCode, data))
                        chooseImage.setText(R.string.change_character_image)
                    }
                }
            }
        }
    }
