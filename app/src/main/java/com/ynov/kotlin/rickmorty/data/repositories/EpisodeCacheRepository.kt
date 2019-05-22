package com.ynov.kotlin.rickmorty.data.repositories

import com.ynov.kotlin.rickmorty.data.ApiManager
import com.ynov.kotlin.rickmorty.data.entity.Character
import com.ynov.kotlin.rickmorty.data.entity.Episode
import com.ynov.kotlin.rickmorty.data.entity.Info
import com.ynov.kotlin.rickmorty.data.remote.CharacterResult
import com.ynov.kotlin.rickmorty.data.remote.EpisodeResult
import io.reactivex.Maybe
import io.reactivex.Single

interface IEpisodeResultCache {
    fun getResult(): EpisodeResult?
    fun getEpisodes(): List<Episode>
    fun setResult(res: EpisodeResult)
    val hasCharacters: Boolean
}

class EpisodeResultCache: IEpisodeResultCache {

    var mResult: EpisodeResult? = null

    override val hasCharacters: Boolean
        get() = mResult != null

    override fun getResult(): EpisodeResult? {
        return mResult
    }

    override fun getEpisodes(): List<Episode> {
        mResult?.let {
            return it.results
        }
        return emptyList()
    }

    override fun setResult(res: EpisodeResult) {
        mResult = res
    }

}

class EpisodeCacheRepository(
    private val delegate: EpisodeRepository,
    private val episodeResCache: IEpisodeResultCache
): IEpisodeRepository by delegate {

    override fun getEpisodes(): Single<EpisodeResult> {
        if (episodeResCache.hasCharacters) {
            return Single.create { emitter ->
                episodeResCache.getResult()?.let {
                    emitter.onSuccess(it)
                }
            }
        } else {
            return delegate.getEpisodes().doOnSuccess { episodes ->
                episodeResCache.setResult(episodes)
            }
        }
    }

}