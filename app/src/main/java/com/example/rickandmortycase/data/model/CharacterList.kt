package com.example.rickandmortycase.data.model

import com.example.rickandmortycase.domain.Character

data class CharacterList(
    var results: List<Character>,
    var info: InfoResponse
)
