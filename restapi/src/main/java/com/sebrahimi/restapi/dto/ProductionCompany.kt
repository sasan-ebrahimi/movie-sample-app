package com.sebrahimi.restapi.dto

import com.google.gson.annotations.SerializedName

data class ProductionCompanyDTO(
    val id: Long,
    @SerializedName("logo_path") val logoPath: String,
    val name: String
)