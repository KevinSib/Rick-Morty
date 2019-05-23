package com.ynov.kotlin.rickmorty.presentation.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ynov.kotlin.rickmorty.R
import com.ynov.kotlin.rickmorty.presentation.extensions.showMessage
import com.ynov.kotlin.rickmorty.presentation.fragments.CharacterDetailFragment
import com.ynov.kotlin.rickmorty.R.layout.activity_character_detail


class CharacterDetailActivity : AppCompatActivity() {

    companion object {
        const val CHARACTER_ID: String = "CHAR_ID"
        fun newIntent(cxt: Context, id: Long): Intent {
            var newI = Intent(cxt, CharacterDetailActivity::class.java)
            newI = newI.putExtra(CHARACTER_ID, id)
            return newI
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setContentView(activity_character_detail)
        loadFragment()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
    private fun loadFragment() {
        val characterId = intent.getLongExtra(CHARACTER_ID, -1L)
        if (characterId == -1L) {
            showMessage(
                getString(R.string.app_name),
                getString(R.string.error_no_character_id)
            )
        } else {
            initFragment(characterId)
        }
    }

    private fun initFragment(id: Long) {
        supportFragmentManager
            .beginTransaction()
            .replace(com.ynov.kotlin.rickmorty.R.id.character_detail_container, CharacterDetailFragment.newInstance(id))
            .commit()
    }

}
