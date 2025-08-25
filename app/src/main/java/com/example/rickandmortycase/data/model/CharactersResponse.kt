package com.example.rickandmortycase.data.model

data class CharacterResponse(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val image: String,
    val gender: String,
    val origin: Origin,
    val location: Location,
    val episode: List<String>,
)

data class InfoResponse(
    val count: Int,
    val pages: Int,
    val next: String,
    val prev: String,
)

data class Origin(
    val name: String?,
    val url: String?,
)

data class Location(
    val name: String?,
    val url: String?,
)
