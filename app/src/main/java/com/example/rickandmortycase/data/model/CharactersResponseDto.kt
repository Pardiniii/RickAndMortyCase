package com.example.rickandmortycase.data.model

import com.example.rickandmortycase.domain.Character
import com.google.gson.annotations.SerializedName
import com.example.rickandmortycase.domain.*

data class CharactersResponseDto(
    @SerializedName("info") val info: InfoDto,
    @SerializedName("results") val results: List<CharacterDto>
)

data class InfoDto(
    val count: Int,
    val pages: Int,
    val next: String,
    val prev: String
)

data class Origin(
    val name: String?,
    val url: String?
)
data class Location(
    val name: String?,
    val url: String?
)

data class CharacterDto(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val image: String,
    val gender: String,
    val origin: Origin,
    val location: Location,
    val episode: List<String>
)

fun CharacterDto.toDomain() = Character(
    id = this.id,
    name = this.name,
    status = this.status,
    species = this.species,
    image = this.image,
    gender = this.gender,
    origin = this.origin.toDomain(),
    location = this.location.toDomain(),
    episode = this.episode
)

fun Origin.toDomain() = com.example.rickandmortycase.domain.Origin(
    name = this.name,
    url = this.url
)

fun Location.toDomain() = com.example.rickandmortycase.domain.Location(
    name = this.name,
    url = this.url
)


