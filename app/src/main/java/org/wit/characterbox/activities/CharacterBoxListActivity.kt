package org.wit.characterbox.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import kotlinx.android.synthetic.main.activity_characterbox_list.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.card_characterbox.view.*
import org.jetbrains.anko.startActivityForResult
import org.wit.characterbox.R
import org.wit.characterbox.main.MainApp
import org.wit.characterbox.models.CharacterBoxModel


class CharacterBoxListActivity : AppCompatActivity() {

    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_characterbox_list)
        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = CharacterBoxAdapter(app.characterboxes)

        toolbarMain.title = title
        setSupportActionBar(toolbarMain)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.item_add -> startActivityForResult<CharacterBoxActivity>(0)
        }
        return super.onOptionsItemSelected(item)
    }
}

class CharacterBoxAdapter constructor(private var characterboxes: List<CharacterBoxModel>) : RecyclerView.Adapter<CharacterBoxAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        return MainHolder(LayoutInflater.from(parent?.context).inflate(R.layout.card_characterbox, parent, false))
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val characterbox = characterboxes[holder.adapterPosition]
        holder.bind(characterbox)
    }

    override fun getItemCount(): Int = characterboxes.size

    class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(characterbox: CharacterBoxModel) {
            itemView.characterName.text = characterbox.cName
            itemView.actorName.text = characterbox.aName
        }
    }
}
