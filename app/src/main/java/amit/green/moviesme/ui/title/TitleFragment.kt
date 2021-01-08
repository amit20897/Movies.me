package amit.green.moviesme.ui.title

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import amit.green.moviesme.R
import amit.green.moviesme.api.model.Title
import amit.green.moviesme.ui.home.TitleContract
import amit.green.moviesme.ui.home.TitleModel
import android.view.*
import kotlinx.android.synthetic.main.fragment_home.*

class TitleFragment : Fragment(), TitleContract.View {

    private lateinit var presenter: TitleContract.Presenter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(false)
        return inflater.inflate(R.layout.fragment_title, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val homeModel = ViewModelProvider(this).get(TitleModel::class.java)
        presenter = TitlePresenter(homeModel, this)
    }

    // region Initialization

//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        inflater.inflate(R.menu.menu_search, menu)
//        super.onCreateOptionsMenu(menu, inflater)
//    }

    // endregion

    // region Lifecycle

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    // endregion

    // region State Update

    // endregion
}