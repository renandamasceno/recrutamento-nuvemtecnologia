package com.renan.marvelnuvem.api.model

import com.google.gson.annotations.SerializedName

data class ThumbnailModel (
    @SerializedName("path")
    val pathMini: String,
    @SerializedName("extension")
    val extensaoMini: String
) {
    fun getImagePath(imageResolution: String? = "detail"): String {
        return "$pathMini/$imageResolution.$extensaoMini".replace("http://", "https://")
    }
}
