package com.ynov.kotlin.rickmorty.data

import com.ynov.kotlin.rickmorty.data.remote.CharacterResult
import com.ynov.kotlin.rickmorty.data.remote.EpisodeResult
import com.ynov.kotlin.rickmorty.data.remote.LocationResult
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private const val API_BASE_URL = "https://rickandmortyapi.com/"

class ApiManager {

    private val service: ApiService

    interface ApiService {
        @GET("api/character")
        fun retrieveCharacters(): Single<CharacterResult>

        @GET("api/location")
        fun retrieveLocation(): Single<LocationResult>

        @GET("api/episode")
        fun retrieveEpisode(): Single<EpisodeResult>
    }

    init {
        service = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    fun retrieveCharacters() = service.retrieveCharacters()

    fun retrieveLocation() = service.retrieveLocation()

    fun retrieveEpisode() = service.retrieveEpisode()
}