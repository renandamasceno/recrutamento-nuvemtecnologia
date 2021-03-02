package com.renan.marvelnuvem.api.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class DatesModel(
    @SerializedName("type")
    val tipo: String?,
    @SerializedName("date")
    val data: Date?
)