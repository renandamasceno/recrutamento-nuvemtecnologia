package com.renan.marvelnuvem.api.model

data class ResponseModel<T>(
    val code: Int?,
    val status: String?,
    val copyright: String?,
    val attributionText: String?,
    val attributionHTML: String?,
    val etag: String?,
    val data: DataModel<T>
)