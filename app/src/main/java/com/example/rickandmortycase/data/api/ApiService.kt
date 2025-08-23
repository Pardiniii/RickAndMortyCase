package com.example.rickandmortycase.data.api

import com.example.rickandmortycase.data.model.Character
import com.example.rickandmortycase.data.model.CharacterDto
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

//    @GET("character")
//    fun getCharacters(
//        @Query("page") page: Int
//    ): Response<CharacterList>

    //para testes no console
    @GET("character")
    fun getCharacters(@Query("page") page: Int): Call<CharacterList>

    @GET("character/{id}")
    fun getCharacterById(@Path("id")id: Int): Call<CharacterDto>
}