package com.ynov.kotlin.rickmorty.data

class DataRepository (
    private val apiManager : ApiManager){

   fun retrieveCaracter () =  apiManager.retrieveCharacters()

    fun retrieveLocation () = apiManager.retrieveLocation()

   fun  retrieveEpisode() = apiManager.retrieveEpisode()

}