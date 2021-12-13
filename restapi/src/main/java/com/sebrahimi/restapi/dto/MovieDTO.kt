package com.sebrahimi.restapi.dto
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieDTO(
    val id: Long,
    val title: String,
    val overview: String?,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("original_language") val originalLanguage: String,
    @SerializedName("backdrop_path") val backdropImage: String,
    @SerializedName("poster_path") val posterImage: String,
    @SerializedName("vote_average") val averageRating: Float,
    @SerializedName("vote_count") val voteCount: Int
): Parcelable
