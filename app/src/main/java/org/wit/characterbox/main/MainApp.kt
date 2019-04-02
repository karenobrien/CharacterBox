package org.wit.characterbox.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.characterbox.models.CharacterBoxJSONStore
import org.wit.characterbox.models.CharacterBoxStore

class MainApp : Application(), AnkoLogger {

    lateinit var characterboxes: CharacterBoxStore


    override fun onCreate() {
        super.onCreate()
        characterboxes = CharacterBoxJSONStore(applicationContext)
        info("Character Box started")

    }
}