package amit.green.moviesme.api.model

import com.google.gson.annotations.SerializedName


data class OMDBSearch (
    @SerializedName("Search")
    val search: List<Title>,

    val totalResults: String,

    @SerializedName("Response")
    val response: String
)