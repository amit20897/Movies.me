package amit.green.moviesme.ui.home

import amit.green.moviesme.api.API
import amit.green.moviesme.api.model.Movie
import amit.green.moviesme.api.model.OMDBSearch
import amit.green.moviesme.api.model.Title
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TitleModel : ViewModel(), TitleContract.Model {

    // region Properties

    override lateinit var title: Title
    override var movie: Movie? = null

    // endregion

    // region Networking

    override fun fetchFullTitle(cb: (Throwable?, Title?) -> Unit) {

    }

    // endregion
}