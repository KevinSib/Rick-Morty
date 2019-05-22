package com.ynov.kotlin.rickmorty.data.repositories

import com.ynov.kotlin.rickmorty.data.ApiManager
import com.ynov.kotlin.rickmorty.data.entity.Character
import com.ynov.kotlin.rickmorty.data.entity.Info
import com.ynov.kotlin.rickmorty.data.remote.CharacterResult
import io.reactivex.Maybe
import io.reactivex.Single

interface ICharacterResultCache {
    fun getResult(): CharacterResult?
    fun getCharacter(id: Long): Maybe<Character>
    fun getCharacters(): List<Character>
    fun setResult(res: CharacterResult)
    val hasCharacters: Boolean
}

class CharacterResultCache: ICharacterResultCache {

    var mResult: CharacterResult? = null

    override val hasCharacters: Boolean
        get() = mResult != null

    override fun getResult(): CharacterResult? {
        return mResult
    }

    override fun getCharacter(id: Long): Maybe<Character> {
        mResult?.let {
            val found = it.results.filter {
                if (it.id == id) {
                    return Maybe.just(it)
                }
                return Maybe.empty()
            }.first()
            return Maybe.just(found)
        }
        return Maybe.empty()
    }

    override fun getCharacters(): List<Character> {
        mResult?.let {
            return it.results
        }
        return emptyList()
    }

    override fun setResult(res: CharacterResult) {
        mResult = res
    }

}

class CharacterCacheRepository(
    private val delegate: CharacterRepository,
    private val characterResCache: ICharacterResultCache
): ICharacterRepository by delegate {

    override fun getCharacters(): Single<CharacterResult> {
        if (characterResCache.hasCharacters) {
            print("from cache")
            return Single.create { emitter ->
                characterResCache.getResult()?.let {
                    emitter.onSuccess(it)
                }
            }
        } else {
            print("from api")
            return delegate.getCharacters().doOnSuccess { characters ->
                characterResCache.setResult(characters)
            }
        }
    }

    override fun getCharacterById(id: Long): Single<Character> {
        return characterResCache.getCharacter(id)
                .switchIfEmpty(delegate.getCharacterById(id))
    }

}