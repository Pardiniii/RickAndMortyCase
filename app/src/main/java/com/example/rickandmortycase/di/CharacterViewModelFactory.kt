package com.example.rickandmortycase.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rickandmortycase.data.repository.Repository
import com.example.rickandmortycase.ui.CharacterViewModel

class CharacterViewModelFactory(
    private val repository: Repository
) : ViewModelProvider.Factory {


    //valida se o repository foi instanciado antes da viewmodel
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CharacterViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CharacterViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
