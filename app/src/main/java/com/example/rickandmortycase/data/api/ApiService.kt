package com.example.rickandmortycase.data.api

import com.example.rickandmortycase.data.model.CharacterDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    //forma correta (assincrona)
    @GET("character")
    suspend fun getCharacters(
        @Query("page") page: Int
    ): Response<CharacterList>

    @GET("character/{id}")
    suspend fun getCharacterById(@Path("id") id: Int): Response<CharacterDto>


}