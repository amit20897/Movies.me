package amit.green.moviesme.ui.title

import amit.green.moviesme.api.model.Movie
import amit.green.moviesme.api.model.Title
import android.view.Menu

interface TitleContract {

    interface Model {

        // region Properties

        var presenter: Presenter?
        var title: Title
        var movie: Movie?

        // endregion

        // region Networking

        fun fetchFullTitle()

        // endregion
    }

    interface View {

        // region Initialization

        // endregion

        // region State Update

        fun startLoading()
        fun stopLoading()
        fun setFullTitle(title: Movie)
        fun showError(message: String?)
        fun updateFavoriteMenuItem(menu: Menu, isFavorite: Boolean)

        // endregion
    }

    interface Presenter {

        // region View Events

        fun onFavoriteButtonClick()

        // endregion

        // region Lifecycle

        fun onDestroy()

        // endregion

        // region Model Events

        fun onFullTitleLoaded(title: Movie)
        fun onFullTitleFetchError(t: Throwable?)
        fun onPrepareOptionsMenu(menu: Menu)

        // endregion
    }
}