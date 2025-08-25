package com.example.rickandmortycase.ui

import CharacterListFragment
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rickandmortycase.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, CharacterListFragment())
                .commit()
        }
    }
}