package com.example.rickandmortycase.ui

import CharacterListFragment
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.rickandmortycase.R
import com.example.rickandmortycase.data.api.RetrofitInstance
import com.example.rickandmortycase.data.repository.Repository
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private val repository = Repository(RetrofitInstance.api)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, CharacterListFragment())
                .commit()
        }


//        val characterId = 754
//        val page = 5

//        thread {
//            val characterList = repository.getCharacters(page)
//            val CharacterSearched = repository.getCharacterById(characterId)
//
//            characterList?.let {
//                characterList.results.forEach{ character ->
//                    Log.d("CHARACTER", "${character.id} - ${character.copy()}")
//                }
//            } ?:Log.e("CHARACTER", "Erro ao buscar personagens")
//
//            CharacterSearched?.let {
//                Log.d("CHARACTER", "${it.id} - ${it.copy()}")
//            } ?: Log.e("CHARACTER", "Erro ao buscar personagem")
//        }

    }
}