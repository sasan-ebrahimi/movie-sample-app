package com.sebrahimi.restapi.dto

import com.google.gson.annotations.SerializedName

data class LanguageDTO(
    @SerializedName("english_name") val englishName: String,
    val name: String
)
