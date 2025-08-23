package com.example.rickandmortycase.data.repository

import com.example.rickandmortycase.data.api.CharacterList
import com.example.rickandmortycase.data.api.RetrofitInstance
import com.example.rickandmortycase.data.model.Character
import retrofit2.Response

class Repository {

    //    fun getCharacters(page: Int): Response<CharacterList> {
//        return RetrofitInstance.api.getCharacters(page)
//    }

    //para testes no console
    fun getCharacters(page: Int): CharacterList? {
        val response = RetrofitInstance.api.getCharacters(page).execute() // executa sincronamente
        return if (response.isSuccessful) response.body() else null
    }

    fun getCharacterById(id: Int): Character?{
        val response = RetrofitInstance.api.getCharacterById(id).execute()
        return if (response.isSuccessful){
            response.body()?.toDomain()
        }else null
    }
}