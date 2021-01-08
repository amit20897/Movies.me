package amit.green.moviesme.api.repository

import amit.green.moviesme.Config
import amit.green.moviesme.api.model.Movie
import amit.green.moviesme.api.model.OMDBSearch
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface MoviesRepository {

    @GET("/")
    fun findById(@Query("i") id: String, @Query("apiKey") apiKey: String = Config.apiKey): Call<Movie?>

    @GET("/")
    fun search(@Query("s") query: String, @Query("page") page: Int = 1, @Query("apiKey") apiKey: String = Config.apiKey): Call<OMDBSearch>
}