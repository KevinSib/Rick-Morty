package com.ynov.kotlin.rickmorty.presentation.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ynov.kotlin.rickmorty.R

class CharacterDetailActivity : AppCompatActivity() {

    companion object {
        val CHARACTER_ID: String = "CHAR_ID"
        fun newIntent(cxt: Context, id: Long): Intent {
            val intent = Intent(cxt, CharacterDetailActivity::class.java)
            intent.putExtra(CHARACTER_ID, id)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_detail)
    }

}
