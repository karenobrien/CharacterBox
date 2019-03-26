package org.wit.characterbox.activities

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.card_characterbox.view.*
import org.wit.characterbox.R
import org.wit.characterbox.models.CharacterBoxModel
import org.wit.characterbox.org.wit.characterbox.helpers.readImageFromPath

interface CharacterBoxListener {
    fun onCharacterBoxClick(characterbox: CharacterBoxModel)
}

class CharacterBoxAdapter constructor(private var characterboxes: List<CharacterBoxModel>, private val listener: CharacterBoxListener) : RecyclerView.Adapter<CharacterBoxAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        return MainHolder(LayoutInflater.from(parent?.context).inflate(R.layout.card_characterbox, parent, false))
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val characterbox = characterboxes[holder.adapterPosition]
        holder.bind(characterbox, listener)
    }

    override fun getItemCount(): Int = characterboxes.size

    class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(characterbox: CharacterBoxModel,  listener : CharacterBoxListener) {
            itemView.characterName.text = characterbox.cName
            itemView.actorName.text = characterbox.aName
            itemView.imageIcon.setImageBitmap(readImageFromPath(itemView.context, characterbox.image))
            itemView.setOnClickListener { listener.onCharacterBoxClick(characterbox) }
        }
    }
}