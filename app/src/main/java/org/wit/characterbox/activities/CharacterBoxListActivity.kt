package org.wit.characterbox.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import kotlinx.android.synthetic.main.activity_characterbox_list.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivityForResult
import org.wit.characterbox.R
import org.wit.characterbox.main.MainApp
import org.wit.characterbox.models.CharacterBoxModel


class CharacterBoxListActivity : AppCompatActivity(), CharacterBoxListener {

    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_characterbox_list)
        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        loadCharacters()

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

    override fun onCharacterBoxClick(characterbox: CharacterBoxModel) {
        startActivityForResult(intentFor<CharacterBoxActivity>().putExtra("characterbox_edit", characterbox), 0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //recyclerView is a widget in activity_placemark_list.xml
        recyclerView.adapter?.notifyDataSetChanged()
        super.onActivityResult(requestCode, resultCode, data)
        loadCharacters()
    }

    private fun loadCharacters() {
        showCharacters(app.characterboxes.findAll())
    }

    fun showCharacters (characterboxes: List<CharacterBoxModel>) {
        recyclerView.adapter = CharacterBoxAdapter(characterboxes, this)
        recyclerView.adapter?.notifyDataSetChanged()
    }
}

