package com.example.rickandmortycase.data.repository

import com.example.rickandmortycase.data.api.ApiService
import com.example.rickandmortycase.data.api.CharacterList
import com.example.rickandmortycase.data.api.RetrofitInstance
import com.example.rickandmortycase.data.model.CharacterDto
import com.example.rickandmortycase.data.model.CharactersResponseDto
import retrofit2.Response

class Repository(private val api: ApiService) {

    //forma correta (assincrona)
    suspend fun getCharacters(page: Int): Response<CharacterList> {
        return RetrofitInstance.api.getCharacters(page)
    }

    suspend fun getCharacterById(id: Int): Response<CharacterDto> {
        return RetrofitInstance.api.getCharacterById(id)
    }

}