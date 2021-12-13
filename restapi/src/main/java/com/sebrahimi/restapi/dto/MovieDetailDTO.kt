package com.sebrahimi.restapi.dto

import com.google.gson.annotations.SerializedName

data class MovieDetailDTO(
    val id: Long,
    val budget: Long,
    val revenue: Long,
    val genres: List<GenreDTO>,
    val popularity: Float,
    @SerializedName("production_companies") val productionCompanies: List<ProductionCompanyDTO>,
    val runtime: Int,
    @SerializedName("spoken_languages") val spokenLanguages: List<LanguageDTO>,
    val status: String,
    val tagline: String
)
