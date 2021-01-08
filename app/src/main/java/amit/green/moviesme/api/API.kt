package amit.green.moviesme.api

import amit.green.moviesme.Config
import amit.green.moviesme.api.repository.MoviesRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object API {

    private val retrofit = Retrofit.Builder()
        .baseUrl(Config.baseURL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val moviesRepository = retrofit.create(MoviesRepository::class.java)
}