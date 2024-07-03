package com.example.appservice

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url


interface ApiService {
    @GET
    suspend fun getListOfSpecies(@Url url: String): Response<DataResponse>
}