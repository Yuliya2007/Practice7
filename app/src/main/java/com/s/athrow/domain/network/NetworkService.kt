package com.s.athrow.domain.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit

object NetworkService {
    @ExperimentalSerializationApi
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://demo1989337.mockable.io/")
        .addConverterFactory(Json.asConverterFactory(MediaType.get("application/json")))
        .build()
    @ExperimentalSerializationApi
    private val restApi = retrofit.create(RestApi::class.java)
    @ExperimentalSerializationApi
    suspend fun loadHammer() = restApi.loadHammer()
    @ExperimentalSerializationApi
    suspend fun loadDisk() = restApi.loadDisk()
    @ExperimentalSerializationApi
    suspend fun loadSpear() = restApi.loadSpear()
    @ExperimentalSerializationApi
    suspend fun loadSportsmens() = restApi.loadSportsmen()
}