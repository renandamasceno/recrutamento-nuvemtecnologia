package com.renan.marvelnuvem.api.repository

import com.renan.marvelnuvem.api.repository.IMarvelEndpoint

class MarvelRepository {

    private val client = IMarvelEndpoint.Endpoint

    suspend fun getComics(offset: Int? = 0) = client.getComics(offset)
}