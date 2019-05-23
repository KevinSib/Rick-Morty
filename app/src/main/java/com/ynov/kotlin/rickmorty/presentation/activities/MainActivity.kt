package com.ynov.kotlin.rickmorty.presentation.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ynov.kotlin.rickmorty.R
import com.ynov.kotlin.rickmorty.presentation.fragments.CharactersFragment
import com.ynov.kotlin.rickmorty.presentation.fragments.EpisodesFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        fun newIntent(cxt: Context): Intent {
            return Intent(cxt, MainActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        initFragment()
    }

    private fun initView() {
        activity_main_bottom_nav_view.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_characters -> {
                    replaceFragment(CharactersFragment.newInstance())
                }
                R.id.navigation_episodes -> {
                    replaceFragment(EpisodesFragment.newInstance())
                }
            }
            false
        }
    }

    private fun initFragment() {
        replaceFragment(CharactersFragment.newInstance())
    }

    private fun replaceFragment(frag: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.activity_main_container, frag)
            .commit()
    }

}
