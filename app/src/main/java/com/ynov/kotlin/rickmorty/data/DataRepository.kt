package com.ynov.kotlin.rickmorty.data

class DataRepository (
    private val apiManager : ApiManager){

   fun RetrieveCaracter () =  apiManager.RetrieveCharacters()

}