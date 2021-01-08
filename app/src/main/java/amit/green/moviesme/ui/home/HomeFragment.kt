package amit.green.moviesme.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import amit.green.moviesme.R
import amit.green.moviesme.api.model.Title
import android.view.*
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(), HomeContract.View {

    private lateinit var presenter: HomeContract.Presenter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val homeModel = ViewModelProvider(this).get(HomeModel::class.java)
        presenter = HomePresenter(homeModel, this)
    }

    // region Initialization

    override fun initRecyclerView() {
        moviesRV.adapter = MoviesAdapter(listOf())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_search, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    // endregion

    // region Lifecycle

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    // endregion

    // region State Update

    override fun updateMovies(movies: List<Title>) {
        val adapter = moviesRV.adapter as? MoviesAdapter ?: return
        adapter.setMovies(movies)
    }

    // endregion
}