package com.ynov.kotlin.rickmorty.data.repositories

import com.ynov.kotlin.rickmorty.data.ApiManager
import com.ynov.kotlin.rickmorty.data.entity.Character
import com.ynov.kotlin.rickmorty.data.entity.Episode
import com.ynov.kotlin.rickmorty.data.remote.CharacterResult
import com.ynov.kotlin.rickmorty.data.remote.EpisodeResult
import io.reactivex.Single

interface IEpisodeRepository {
    fun getEpisodes(): Single<EpisodeResult>
}

class EpisodeRepository(private val apiManager: ApiManager): IEpisodeRepository {

    override fun getEpisodes(): Single<EpisodeResult> = apiManager.retrieveEpisode()

}