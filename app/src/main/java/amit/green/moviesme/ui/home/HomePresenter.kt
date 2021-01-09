package amit.green.moviesme.ui.home

import amit.green.moviesme.api.model.Title
import android.os.Handler
import java.util.*

class HomePresenter(var model: HomeContract.Model, view: HomeContract.View) :
    HomeContract.Presenter {

    companion object {
        const val DEFAULT_SEARCH = "the pirates"
    }

    private var view: HomeContract.View? = view
    private var searchDelayTimer: Timer? = null

    init {
        model.presenter = this
    }

    init {
        view.initRecyclerView()

        if (model.movies.isNotEmpty()) {
            view.setMovies(model.movies)
            if (model.latestVisiblePosition != 0) {
                view.scrollToPosition(model.latestVisiblePosition)
            }
        } else fetchMovies()
    }

    // region Lifecycle

    override fun onDestroy() {
        view = null
    }

    override fun onFirstVisibleItemPositionChanged(itemPosition: Int) {
        model.latestVisiblePosition = itemPosition
    }

    // endregion

    // region View Events

    override fun onItemClick(title: Title, position: Int) {
        view?.moveToTitleFragment(title)
    }

    override fun onLastItemReached() {
        if (model.hasReachedEnd) return
        fetchMovies(model.currentSearch)
    }

    override fun onQueryTextSubmit(query: String?) {
        // do nothing
    }

    override fun onQueryTextChange(newText: String?) {
        searchDelayTimer?.cancel()
        val timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                fetchMovies(newText)
            }
        }, 400)
        searchDelayTimer = timer
    }

    // endregion

    // region Model Events

    override fun onMoviesReceived(movies: List<Title>, page: Int, search: String) {
        if (search != model.currentSearch) return

        view?.stopLoading()
        if (page == 1) view?.setMovies(movies)
        else view?.addMovies(movies)
        model.currentPage++
        if (movies.size < 10) model.hasReachedEnd = true
    }

    override fun onMoviesFetchFailed(e: Throwable?, page: Int, search: String) {
        if (search != model.currentSearch) return

        view?.stopLoading()
        view?.showError(e?.message)
    }

    // endregion

    // region Private functions

    private fun fetchMovies(search: String? = null) {
        val clnSearch = if (search?.isNotEmpty() == true) search else DEFAULT_SEARCH

        if (model.currentSearch != search) {
            model.currentSearch = clnSearch
            model.currentPage = 0
            model.hasReachedEnd = false
        }
        view?.startLoading()
        model.fetchMovies(clnSearch, model.currentPage + 1)
    }

    // endregion
}