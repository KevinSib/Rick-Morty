package com.ynov.kotlin.rickmorty.data.remote

import com.ynov.kotlin.rickmorty.data.entity.Character
import com.ynov.kotlin.rickmorty.data.entity.Info

data class CharacterResult (
    val info: Info,
    val results: List<Character>
)
