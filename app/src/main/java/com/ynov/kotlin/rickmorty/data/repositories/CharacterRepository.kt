package com.ynov.kotlin.rickmorty.data.repositories

import com.ynov.kotlin.rickmorty.data.ApiManager
import com.ynov.kotlin.rickmorty.data.entity.Character
import com.ynov.kotlin.rickmorty.data.remote.CharacterResult
import io.reactivex.Single

interface ICharacterRepository {
    fun getCharacters(): Single<CharacterResult>
    fun getCharacterById(id: Long): Single<Character>
}

class CharacterRepository(private val apiManager: ApiManager): ICharacterRepository {

    override fun getCharacters(): Single<CharacterResult> = apiManager.retrieveCharacters()

    override fun getCharacterById(id: Long): Single<Character> = apiManager.retrieveDetailCharacter("$id")
    // TODO pour le "$id" c'est pas mal mais pas très explicite
    //  le mieux est de caster : id.toString()
    //  comme ça on comprend bien ce que vous voulez faire ici

}