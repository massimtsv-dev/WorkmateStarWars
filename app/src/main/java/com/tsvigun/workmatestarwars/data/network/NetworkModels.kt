package com.tsvigun.workmatestarwars.data.network

import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    @SerializedName("results") val results: List<NetworkCharacter>
)

data class NetworkCharacter(
    @SerializedName("name") val name: String?,
    @SerializedName("birth_year") val birthYear: String?,
    @SerializedName("height") val height: String?,
    @SerializedName("mass") val mass: String?,
    @SerializedName("url") val url: String?,
    @SerializedName("films") val films: List<String>?
)