package com.example.rickandmortycase.data.repository

import com.example.rickandmortycase.data.api.ApiService
import com.example.rickandmortycase.data.api.CharacterList
import com.example.rickandmortycase.data.api.RetrofitInstance
import com.example.rickandmortycase.data.model.Character
import com.example.rickandmortycase.data.model.CharacterDto
import com.example.rickandmortycase.data.model.toDomain
import retrofit2.Response

class Repository(private val api: ApiService) {

    //forma correta (assincrona)
    suspend fun getCharacters(page: Int): Response<CharacterList> {
        return RetrofitInstance.api.getCharacters(page)
    }

    suspend fun getCharacterById(id: Int): Response<CharacterDto> {
        return RetrofitInstance.api.getCharacterById(id)
    }

    //para testes no console de forma sincrona
//    fun getCharacters(page: Int): CharacterList? {
//        val response = RetrofitInstance.api.getCharacters(page).execute() // executa sincronamente
//        return if (response.isSuccessful) response.body() else null
//    }
//
//    fun getCharacterById(id: Int): Character?{
//        val response = RetrofitInstance.api.getCharacterById(id).execute()
//        return if (response.isSuccessful){
//            response.body()?.toDomain()
//        }else null
//    }
}