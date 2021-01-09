package amit.green.moviesme.ui.home

import amit.green.moviesme.R
import amit.green.moviesme.api.model.Title
import amit.green.moviesme.ui.FavoritesViewModel
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(), HomeContract.View, MoviesAdapter.MoviesAdapterListener,
    SearchView.OnQueryTextListener {

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

        val activity = requireActivity()
        val homeModel = ViewModelProvider(activity).get(HomeModel::class.java)
        val favoritesViewModel = ViewModelProvider(activity).get(FavoritesViewModel::class.java)
        presenter = HomePresenter(homeModel, this, favoritesViewModel)
    }

    // region Initialization

    override fun initRecyclerView() {
        moviesRV.adapter = MoviesAdapter(listOf(), this)
        moviesRV.addOnScrollListener(object : OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = moviesRV.layoutManager as? GridLayoutManager ?: return
                val visibleItemCount: Int = layoutManager.childCount
                val totalItemCount: Int = layoutManager.itemCount
                val firstVisibleItemPosition: Int = layoutManager.findFirstVisibleItemPosition()
                presenter.onFirstVisibleItemPositionChanged(firstVisibleItemPosition)
                if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0) {
                    presenter.onLastItemReached()
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_search, menu)
        super.onCreateOptionsMenu(menu, inflater)

        val item = menu.findItem(R.id.app_bar_search)
        val searchView = item.actionView as SearchView
        searchView.setOnQueryTextListener(this)
        searchView.isIconified = false
    }

    // endregion

    // region Lifecycle

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    // endregion

    // region State Update

    override fun setMovies(movies: List<Title>) {
        val adapter = moviesRV.adapter as? MoviesAdapter ?: return
        adapter.setMovies(movies)
    }

    override fun addMovies(movies: List<Title>) {
        val adapter = moviesRV.adapter as? MoviesAdapter ?: return
        adapter.addMovies(movies)
    }

    override fun scrollToPosition(position: Int) {
        moviesRV.scrollToPosition(position)
    }

    override fun startLoading() {

    }

    override fun stopLoading() {

    }

    override fun showError(message: String?) {
        Snackbar.make(
            requireView(),
            message ?: requireContext().getString(R.string.error_movies_fetch),
            Snackbar.LENGTH_SHORT
        ).show()
    }

    // endregion

    // region Navigation

    override fun moveToTitleFragment(title: Title) {
        findNavController().navigate(
            HomeFragmentDirections.actionNavigationHomeToTitleFragment(
                title,
                title.title
            )
        )
    }

    // endregion

    // region Movies Adapter Listener

    override fun onItemClick(adapter: MoviesAdapter, item: Title, position: Int) {
        presenter.onItemClick(item, position)
    }

    override fun isTitleFavorite(adapter: MoviesAdapter, title: Title, position: Int): Boolean {
        return presenter.onIsTitleFavorite(title)
    }

    // endregion

    // region Search View Listener

    override fun onQueryTextSubmit(query: String?): Boolean {
        presenter.onQueryTextSubmit(query)
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        presenter.onQueryTextChange(newText)
        return true
    }

    // endregion
}