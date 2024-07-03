package com.example.appservice
data class DataResponse(
    val results: List<Results>
)

data class Results(
    val id : Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val image: String,
    val location: Location
)

data class Location(
    val name: String
)