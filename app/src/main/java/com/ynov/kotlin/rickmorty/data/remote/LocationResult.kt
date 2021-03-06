package com.ynov.kotlin.rickmorty.data.remote

import com.ynov.kotlin.rickmorty.data.entity.Info
import com.ynov.kotlin.rickmorty.data.entity.Location

data class LocationResult (
    val info: Info,
    val results: List<Location>
)