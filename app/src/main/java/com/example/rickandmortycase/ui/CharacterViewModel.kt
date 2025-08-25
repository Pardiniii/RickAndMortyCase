package com.example.rickandmortycase.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortycase.data.repository.CharacterRepository
import com.example.rickandmortycase.domain.Character
import kotlinx.coroutines.launch

class CharacterViewModel(private val repository: CharacterRepository) : ViewModel(){

    private val _characters = MutableLiveData<List<Character>>()
    val characters: LiveData<List<Character>> = _characters

    private val _totalPages = MutableLiveData<Int>()
    val totalPages: LiveData<Int> = _totalPages

    fun fetchCharacters(page: Int ){
        viewModelScope.launch {
            try {
                val response = repository.getCharacters(page)
                if (response.isSuccessful){
                    val body = response.body()
                    val characters = body?.results ?: emptyList()
                    _characters.postValue(characters)

                    _totalPages.postValue(body?.info?.pages)
                }else{
                    _characters.postValue(emptyList())
                }
            }catch (e: Exception){
                e.printStackTrace()
                _characters.postValue(emptyList())
            }
        }
    }
}