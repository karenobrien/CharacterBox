package org.wit.characterbox.models

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.jetbrains.anko.AnkoLogger
import org.wit.characterbox.org.wit.characterbox.helpers.*
import java.util.*

val JSON_FILE = "characters.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<java.util.ArrayList<CharacterBoxModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}


class CharacterBoxJSONStore : CharacterBoxStore, AnkoLogger {
    val context: Context
    var characterboxes = mutableListOf<CharacterBoxModel>()

    constructor (context: Context) {
        this.context = context
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<CharacterBoxModel> {
        return characterboxes
    }

    override fun create(characterbox: CharacterBoxModel) {
        characterbox.id = generateRandomId()
        characterboxes.add(characterbox)
        serialize()
    }


    override fun update(characterbox: CharacterBoxModel) {
        val characterList = findAll() as ArrayList<CharacterBoxModel>
        var foundCharacter: CharacterBoxModel? = characterList.find { p -> p.id == characterbox.id }
        if (foundCharacter != null) {
            foundCharacter.cName = characterbox.cName
            foundCharacter.aName = characterbox.aName
            foundCharacter.image = characterbox.image

        }
        serialize()
    }

    override fun delete(characterbox: CharacterBoxModel) {
        characterboxes.remove(characterbox)
        serialize()
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(characterboxes, listType)
        write(context, JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
        characterboxes = Gson().fromJson(jsonString, listType)
    }

}