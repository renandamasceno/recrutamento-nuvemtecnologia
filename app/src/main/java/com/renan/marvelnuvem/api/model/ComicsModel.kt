package com.renan.marvelnuvem.api.model

import com.google.gson.annotations.SerializedName

data class ComicsModel (
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val titulo: String,
    @SerializedName("issueNumber")
    val numeroEdicao: Double,
    @SerializedName("description")
    val descricao: String,
    @SerializedName("pageCount")
    val paginacao: Int,
    @SerializedName("dates")
    val datas: List<DatesModel>,
    @SerializedName("prices")
    val precos: List<PrecosModel>,
    @SerializedName("thumbnail")
    val thumbnail: ThumbnailModel?,
    @SerializedName("images")
    val imagem: List<ThumbnailModel>

)