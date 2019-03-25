package org.wit.characterbox.models

interface CharacterBoxStore {
    fun findAll(): List<CharacterBoxModel>
    fun create(characterbox: CharacterBoxModel)
    fun update(characterbox: CharacterBoxModel)
}