package amit.green.moviesme.ui.home

class HomePresenter(var model: HomeContract.Model, view: HomeContract.View) :
    HomeContract.Presenter {

    private var view: HomeContract.View? = view

    init {
        view.initRecyclerView()

//        model.fetchMovies { e, movies -> view.updateMovies(movies ?: listOf()) }
    }

    // region Lifecycle

    override fun onDestroy() {
        view = null
    }

    // endregion

    // region View Events


    // endregion

    // region Private functions


    // endregion
}