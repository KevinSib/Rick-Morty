package com.ynov.kotlin.rickmorty.data.repositories

import com.ynov.kotlin.rickmorty.data.ApiManager

class DataRepository(
    private val apiManager: ApiManager
) {

    fun retrieveCharacter() = apiManager.retrieveCharacters()

    fun retrieveLocation() = apiManager.retrieveLocation()

    fun retrieveEpisode() = apiManager.retrieveEpisode()

    fun retrieveDetailCharacter(charactId: String) = apiManager.retrieveDetailCharacter(charactId)

}