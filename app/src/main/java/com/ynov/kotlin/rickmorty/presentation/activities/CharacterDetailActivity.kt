package com.ynov.kotlin.rickmorty.presentation.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ynov.kotlin.rickmorty.R
import com.ynov.kotlin.rickmorty.presentation.extensions.showMessage
import com.ynov.kotlin.rickmorty.presentation.fragments.CharacterDetailFragment
import com.ynov.kotlin.rickmorty.presentation.fragments.CharactersFragment

class CharacterDetailActivity : AppCompatActivity() {

    companion object {
        val CHARACTER_ID: String = "CHAR_ID"
        fun newIntent(cxt: Context, id: Long): Intent {
            var newI = Intent(cxt, CharacterDetailActivity::class.java)
            newI = newI.putExtra(CHARACTER_ID, id)
            return newI
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_detail)
        loadFragment()
    }

    private fun loadFragment() {
        val characterId = intent.getLongExtra(CHARACTER_ID, -1L)
        if (characterId == -1L) {
            //  TODO put string on strings.xml
            showMessage(
                "Ricky et Morty",
                "Une erreur est survenu, merci de réessayer plus tard"
            )
        } else {
            initFragment(characterId)
        }
    }

    private fun initFragment(id: Long) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.character_detail_container, CharacterDetailFragment.newInstance(id))
            .commit()
    }

}
