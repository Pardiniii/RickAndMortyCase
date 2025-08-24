package com.example.rickandmortycase.data.api

import com.example.rickandmortycase.data.model.Character
import com.example.rickandmortycase.data.model.InfoDto

data class CharacterList(
    var results: List<Character>,
    var info: InfoDto
)
