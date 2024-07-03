package com.example.appservice
data class DataResponse(
    val results: List<Results>
)

data class Results( //la lista en si
    val id : Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val image: String,
    val location: Location
)

data class Location( //no devuelve string, sino que devuelve objeto, entonces debo hacer una data class
    val name: String
)