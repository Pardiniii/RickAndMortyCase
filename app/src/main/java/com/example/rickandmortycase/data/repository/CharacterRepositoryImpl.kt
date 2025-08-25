package com.example.rickandmortycase.data.repository

import com.example.rickandmortycase.data.api.RetrofitInstance
import com.example.rickandmortycase.data.model.CharacterList
import retrofit2.Response

class CharacterRepositoryImpl : CharacterRepository {

    override suspend fun getCharacters(page: Int): Response<CharacterList> {
        return RetrofitInstance.api.getCharacters(page)
    }

}