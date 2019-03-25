package org.wit.characterbox.models

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class CharacterBoxMemStore : CharacterBoxStore, AnkoLogger {
    val characterboxes = ArrayList<CharacterBoxModel>()

    override fun findAll(): List<CharacterBoxModel> {
        return characterboxes
    }

    override fun create(characterbox: CharacterBoxModel) {
        characterbox.id = getId()
        characterboxes.add(characterbox)
        logAll()
    }

    override fun update(characterbox: CharacterBoxModel) {
        var foundCharacterBox: CharacterBoxModel? = characterboxes.find { p -> p.id == characterbox.id }
        if (foundCharacterBox != null) {
            foundCharacterBox.cName = characterbox.cName
            foundCharacterBox.aName = characterbox.aName
            logAll()
        }
    }

    fun logAll() {
        characterboxes.forEach{ info("${it}") }
    }
}