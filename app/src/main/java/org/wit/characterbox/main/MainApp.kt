package org.wit.characterbox.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.characterbox.models.CharacterBoxMemStore
import org.wit.characterbox.models.CharacterBoxModel

class MainApp : Application(), AnkoLogger {

    //val characterboxes = ArrayList<CharacterBoxModel>()
    val characterboxes = CharacterBoxMemStore()

    override fun onCreate() {
        super.onCreate()
        info("Character Box started")
        //characterboxes.add(CharacterBoxModel("One", "About one..."))
        //characterboxes.add(CharacterBoxModel("Two", "About two..."))
        //characterboxes.add(CharacterBoxModel("Three", "About three..."))
    }
}