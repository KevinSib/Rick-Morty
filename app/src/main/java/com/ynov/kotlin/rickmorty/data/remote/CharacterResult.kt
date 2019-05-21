package com.ynov.kotlin.rickmorty.data.remote

import com.ynov.kotlin.rickmorty.data.entity.Info
import com.ynov.kotlin.rickmorty.data.entity.Result

data class CharacterResult (
    val info: Info,
    val results: List<Result>
)
