package amit.green.moviesme.ui.home

import amit.green.moviesme.api.model.Title

interface HomeContract {

    interface Model {

        // region Properties

        var presenter: Presenter?
        var movies: ArrayList<Title>
        var hasReachedEnd: Boolean
        var currentPage: Int
        var currentSearch: String
        var latestVisiblePosition: Int

        // endregion

        // region Networking

        fun fetchMovies(search: String, page: Int = 0)

        // endregion
    }

    interface View {

        // region Initialization

        fun initRecyclerView()

        // endregion

        // region State Update

        fun setMovies(movies: List<Title>)
        fun addMovies(movies: List<Title>)

        // endregion

        // region Navigation

        fun moveToTitleFragment(title: Title)
        fun startLoading()
        fun stopLoading()
        fun showError(message: String?)
        fun scrollToPosition(position: Int)

        // endregion
    }

    interface Presenter {

        // region Lifecycle

        fun onDestroy()

        // endregion

        // region View Events

        fun onItemClick(title: Title, position: Int)
        fun onLastItemReached()
        fun onQueryTextSubmit(query: String?)
        fun onQueryTextChange(newText: String?)

        // endregion

        // region Model Events

        fun onMoviesReceived(movies: List<Title>, page: Int, search: String)
        fun onMoviesFetchFailed(e: Throwable?, page: Int, search: String)
        fun onFirstVisibleItemPositionChanged(itemPosition: Int)

        // endregion
    }
}