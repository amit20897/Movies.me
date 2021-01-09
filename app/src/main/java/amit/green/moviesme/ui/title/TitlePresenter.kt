package amit.green.moviesme.ui.title

import amit.green.moviesme.api.model.Movie
import amit.green.moviesme.ui.FavoritesViewModel
import android.view.Menu

class TitlePresenter(
    var model: TitleContract.Model,
    view: TitleContract.View,
    args: TitleFragmentArgs,
    val favoritesViewModel: FavoritesViewModel
) :
    TitleContract.Presenter {

    private var view: TitleContract.View? = view

    init {
        model.presenter = this
        model.title = args.title
    }

    init {
        view.startLoading()
        model.fetchFullTitle()
    }

    // region Lifecycle

    override fun onDestroy() {
        view = null
    }

    // endregion

    // region View Events

    override fun onFavoriteButtonClick() {
        val movie = model.movie ?: return
        val isFavorite = favoritesViewModel.isFavorite(movie)
        if (!isFavorite) favoritesViewModel.addFavorite(movie)
        else favoritesViewModel.removeFavorite(movie)

        view?.invalidateOptionsMenu()
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        val isFavorite = favoritesViewModel.isFavorite(model.title.imdbID ?: "")
        view?.updateFavoriteMenuItem(menu, isFavorite)
    }

    // endregion

    // region Model Events

    override fun onFullTitleLoaded(title: Movie) {
        view?.stopLoading()
        view?.setFullTitle(title)
    }

    override fun onFullTitleFetchError(t: Throwable?) {
        view?.stopLoading()
        view?.showError(t?.message)
    }

    // endregion

    // region Private functions


    // endregion
}