package com.example.rickandmortycase.data.model

import com.google.gson.annotations.SerializedName

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

//data class Origin(
//    val name: String?,
//    val url: String?
//)
//data class Location(
//    val name: String?,
//    val url: String?
//)

data class CharacterDto(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val image: String,
    val gender: String,
//    val origin: Origin,
//    val location: Location,
    val episode: List<String>
)

fun CharacterDto.toDomain() = Character(
    id = this.id,
    name = this.name,
    status = this.status,
    species = this.species,
    image = this.image,
    gender = this.gender,
//    origin = this.origin.name?: "",
//    location = this.location.name?: "",
    episode = this.episode
)

