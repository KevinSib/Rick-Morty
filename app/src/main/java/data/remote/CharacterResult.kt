package data.remote

import data.entity.Info
import data.entity.Result

data class CharacterResult (
    val info: Info,
    val results: List<Result>
)
