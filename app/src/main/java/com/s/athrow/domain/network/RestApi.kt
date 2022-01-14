package com.s.athrow.domain.network

import com.s.athrow.data.model.Information
import com.s.athrow.data.model.Sportsmen
import retrofit2.http.GET

interface RestApi {
    @GET("hammer")
    suspend fun loadHammer(): List<Information>
    @GET("disk")
    suspend fun loadDisk(): List<Information>
    @GET("spear")
    suspend fun loadSpear(): List<Information>
    @GET("sportsmens")
    suspend fun loadSportsmen(): List<Sportsmen>
}