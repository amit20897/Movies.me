package amit.green.moviesme.ui.title

import amit.green.moviesme.api.API
import amit.green.moviesme.api.model.Movie
import amit.green.moviesme.api.model.Title
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TitleModel : ViewModel(), TitleContract.Model {

    // region Properties

    override var presenter: TitleContract.Presenter? = null
    override lateinit var title: Title
    override var movie: Movie? = null

    // endregion

    // region Networking

    override fun fetchFullTitle() {
        val id = title.imdbID
        if (id == null) {
            presenter?.onFullTitleFetchError(null)
            return
        }
        API.moviesRepository.findById(id).enqueue(object : Callback<Movie?> {
            override fun onResponse(call: Call<Movie?>, response: Response<Movie?>) {
                val movie = response.body()
                if (movie == null) {
                    presenter?.onFullTitleFetchError(null)
                    return
                }
                this@TitleModel.movie = movie
                presenter?.onFullTitleLoaded(movie)
            }

            override fun onFailure(call: Call<Movie?>, t: Throwable) {
                presenter?.onFullTitleFetchError(t)
            }
        })
    }

    // endregion
}