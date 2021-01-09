package amit.green.moviesme.ui.home

import amit.green.moviesme.api.API
import amit.green.moviesme.api.model.OMDBSearch
import amit.green.moviesme.api.model.Title
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeModel : ViewModel(), HomeContract.Model {

    // region Properties

    override var presenter: HomeContract.Presenter? = null
    override var movies: ArrayList<Title> = arrayListOf()
    override var hasReachedEnd: Boolean = false
    override var currentPage: Int = 0
    override var currentSearch: String = ""
    override var latestVisiblePosition: Int = 0
    private var isLoading = false

    // endregion

    // region Networking

    override fun fetchMovies(search: String, page: Int) {
        if (isLoading) return
        isLoading = true
        API.moviesRepository.search(search, page).enqueue(object: Callback<OMDBSearch> {
            override fun onResponse(call: Call<OMDBSearch>, response: Response<OMDBSearch>) {
                isLoading = false
                val movies = response.body()?.search
                if (movies == null) {
                    presenter?.onMoviesFetchFailed(null, page, search)
                } else {
                    this@HomeModel.movies.addAll(movies)
                    presenter?.onMoviesReceived(movies, page, search)
                }
            }

            override fun onFailure(call: Call<OMDBSearch>, t: Throwable) {
                isLoading = false
                presenter?.onMoviesFetchFailed(t, page, search)
            }
        })
    }

    // endregion
}