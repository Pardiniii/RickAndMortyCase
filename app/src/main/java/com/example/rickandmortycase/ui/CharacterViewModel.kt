package com.example.rickandmortycase.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortycase.data.model.Character
import com.example.rickandmortycase.data.repository.Repository
import kotlinx.coroutines.launch

class CharacterViewModel(private val repository: Repository) : ViewModel(){
    private val _characters = MutableLiveData<List<Character>>()
    val characters: LiveData<List<Character>> = _characters

    fun fetchCharacters(page: Int ){
        viewModelScope.launch {
            try {
                val response = repository.getCharacters(page)
                if (response.isSuccessful){
                    val characters = response.body()?.results ?: emptyList()
                    _characters.postValue(characters)
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