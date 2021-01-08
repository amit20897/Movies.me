package amit.green.moviesme.ui.home

import amit.green.moviesme.api.API
import amit.green.moviesme.api.model.Movie
import amit.green.moviesme.api.model.OMDBSearch
import amit.green.moviesme.api.model.Title
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeModel : ViewModel(), HomeContract.Model {

    // region Properties

    override var movies: ArrayList<Movie> = arrayListOf()

    // endregion

    // region Networking

    override fun fetchMovies(cb: (Throwable?, List<Title>?) -> Unit) {
        API.moviesRepository.search("the pirates").enqueue(object: Callback<OMDBSearch> {
            override fun onResponse(call: Call<OMDBSearch>, response: Response<OMDBSearch>) {
                cb(null, response.body()?.search)
            }

            override fun onFailure(call: Call<OMDBSearch>, t: Throwable) {
                cb(t, null)
            }
        })
    }

    // endregion
}