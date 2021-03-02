package com.renan.marvelnuvem.api.repository

import com.renan.marvelnuvem.api.model.ComicsModel
import com.renan.marvelnuvem.api.model.ResponseModel
import com.renan.marvelnuvem.api.netwok.NetworkUtils
import retrofit2.http.GET
import retrofit2.http.Query

interface IMarvelEndpoint {
    @GET("/v1/public/comics")
    suspend fun getComics(@Query("offset") offset: Int? = 0): ResponseModel<ComicsModel>

    companion object {
        val Endpoint: IMarvelEndpoint by lazy {
            NetworkUtils.getRetrofitInstance().create(IMarvelEndpoint::class.java)
        }
    }
}