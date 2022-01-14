package com.s.athrow.domain.network

import android.content.Context
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit

class NetworkService(context: Context) {
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
}
object NetworkService2 {
    @ExperimentalSerializationApi
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://demo1989337.mockable.io/")
        .addConverterFactory(Json.asConverterFactory(MediaType.get("application/json")))
        .build()
    @ExperimentalSerializationApi
    private val restApi = retrofit.create(RestApi::class.java)
    @ExperimentalSerializationApi
    suspend fun loadSportsmens() = restApi.loadSportsmen()
}