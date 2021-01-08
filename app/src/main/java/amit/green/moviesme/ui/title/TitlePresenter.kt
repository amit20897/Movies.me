package amit.green.moviesme.ui.title

import amit.green.moviesme.ui.home.TitleContract

class TitlePresenter(var model: TitleContract.Model, view: TitleContract.View) :
    TitleContract.Presenter {

    private var view: TitleContract.View? = view

    init {

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