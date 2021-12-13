package com.sebrahimi.restapi.dto
import com.google.gson.annotations.SerializedName
data class ApiResponseDTO<T>(
    val page: Int,
    val results: T,
    @SerializedName("total_pages") val totalPages: Int
)