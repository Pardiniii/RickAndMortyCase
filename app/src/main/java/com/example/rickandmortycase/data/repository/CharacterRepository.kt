package com.example.rickandmortycase.data.repository

import com.example.rickandmortycase.data.api.RetrofitInstance
import com.example.rickandmortycase.data.model.CharacterList
import retrofit2.Response

interface CharacterRepository {
    suspend fun getCharacters(page: Int): Response<CharacterList>

}