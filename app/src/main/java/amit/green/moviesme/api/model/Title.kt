package amit.green.moviesme.api.model

import com.google.gson.annotations.SerializedName


data class Title (
    @SerializedName("Title")
    val title: String,

    @SerializedName("Year")
    val year: String,

    val imdbID: String,

    @SerializedName("Type")
    val type: Type,

    @SerializedName("Poster")
    val poster: String
)

enum class Type {
    @SerializedName("movie")
    Movie,

    @SerializedName("series")
    Series,

    @SerializedName("episode")
    Episode;
}