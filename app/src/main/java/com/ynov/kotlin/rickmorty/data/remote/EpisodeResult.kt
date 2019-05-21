package com.ynov.kotlin.rickmorty.data.remote

import com.ynov.kotlin.rickmorty.data.entity.Episode
import com.ynov.kotlin.rickmorty.data.entity.Info

data class EpisodeResult (
    val info: Info,
    val results: List<Episode>
)