package com.ynov.kotlin.rickmorty.data.repositories

import com.ynov.kotlin.rickmorty.data.ApiManager
import com.ynov.kotlin.rickmorty.data.entity.Character
import com.ynov.kotlin.rickmorty.data.remote.CharacterResult
import io.reactivex.Maybe
import io.reactivex.Single

interface ICharacterCache {
    fun getCharacter(id: Long): Maybe<Character>
    fun putCharacter(character: Character)
}

class CharacterCache: ICharacterCache {

    var mCharacters: MutableList<Character> = mutableListOf()

    override fun getCharacter(id: Long): Maybe<Character> {
        val found = mCharacters.filter {
            if (it.id == id) {
                return Maybe.just(it)
            }
            return Maybe.empty()
        }.first()
        return Maybe.just(found)
    }

    override fun putCharacter(character: Character) {
        mCharacters.add(character)
    }

}

class CharacterCacheRepository(
    private val delegate: CharacterRepository,
    private val characterCache: ICharacterCache
): ICharacterRepository by delegate {

    override fun getCharacters(): Single<CharacterResult> {
        return delegate.getCharacters().doOnSuccess { characters ->
            characters.results.forEach { character ->
                characterCache.putCharacter(character)
            }
        }
    }

    override fun getCharacterById(id: Long): Single<Character> {
        return characterCache.getCharacter(id)
                .switchIfEmpty(delegate.getCharacterById(id)
                .doOnSuccess { characterCache.putCharacter(it) })
    }

}