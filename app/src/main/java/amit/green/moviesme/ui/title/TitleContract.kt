package amit.green.moviesme.ui.home

import amit.green.moviesme.api.model.Movie
import amit.green.moviesme.api.model.Title

interface TitleContract {

    interface Model {

        // region Properties

        var title: Title
        var movie: Movie?

        // endregion

        // region Networking

        fun fetchFullTitle(cb: (Throwable?, Title?) -> Unit)

        // endregion
    }

    interface View {

        // region Initialization

        // endregion

        // region State Update

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