package com.ynov.kotlin.rickmorty.data

import com.ynov.kotlin.rickmorty.data.remote.CharacterResult
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import io.reactivex.Single
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

private const val API_BASE_URL = "https://rickandmortyapi.com/"

class ApiManager {

    private val service: ApiService

    interface ApiService {

        @GET("api/character")
        fun retrieveCharacters(): Single<CharacterResult>

    }

    init {
        service = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

   fun RetrieveCharacters() = service.retrieveCharacters()
}