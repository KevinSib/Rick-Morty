package com.ynov.kotlin.rickmorty.data.entity

data class Info (
    val count: Long,
    val pages: Long,
    val next: String,
    val prev: String
)