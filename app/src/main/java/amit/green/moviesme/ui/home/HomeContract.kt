package amit.green.moviesme.ui.home

import amit.green.moviesme.api.model.Movie
import amit.green.moviesme.api.model.Title

interface HomeContract {

    interface Model {

        // region Properties

        var movies: ArrayList<Movie>

        // endregion

        // region Networking

        fun fetchMovies(cb: (Throwable?, List<Title>?) -> Unit)

        // endregion
    }

    interface View {

        // region Initialization

        fun initRecyclerView()

        // endregion

        // region State Update

        fun updateMovies(movies: List<Title>)

        // endregion
    }

    interface Presenter {

        // region View Events

        // endregion

        // region Lifecycle

        fun onDestroy()

        // endregion
    }
}