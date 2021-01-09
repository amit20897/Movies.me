package amit.green.moviesme.ui.title

import amit.green.moviesme.R
import amit.green.moviesme.api.model.Movie
import amit.green.moviesme.ui.FavoritesViewModel
import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_title.*

class TitleFragment : Fragment(), TitleContract.View {

    private lateinit var presenter: TitleContract.Presenter
    private val args: TitleFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_title, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val homeModel = ViewModelProvider(this).get(TitleModel::class.java)
        val favoritesViewModel =
            ViewModelProvider(requireActivity()).get(FavoritesViewModel::class.java)
        presenter = TitlePresenter(homeModel, this, args, favoritesViewModel)
    }

    // region Initialization

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_title, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        presenter.onPrepareOptionsMenu(menu)
    }

    override fun updateFavoriteMenuItem(menu: Menu, isFavorite: Boolean) {
        menu.findItem(R.id.favorite)?.icon = ContextCompat.getDrawable(
            requireActivity(),
            if (isFavorite) R.drawable.ic_favorite_black_24dp else R.drawable.ic_favorite_border_black_24dp
        )
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.favorite -> {
                presenter.onFavoriteButtonClick()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    // endregion

    // region Lifecycle

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    // endregion

    // region State Update

    override fun startLoading() {

    }

    override fun stopLoading() {

    }

    override fun setFullTitle(title: Movie) {
        titleTV.text = title.title
        ratingTV.text = title.imdbRating
        genreTV.text = title.genre
        runtimeTV.text = title.runtime
        releaseDateTV.text = title.released
        plotTV.text = title.plot

        Glide.with(this)
            .load(title.poster)
            .fitCenter()
            .placeholder(R.drawable.bg_rating)
            .into(posterImageView)
    }

    override fun showError(message: String?) {
        Snackbar.make(
            requireView(),
            message ?: getString(R.string.error_full_title_fetch),
            Snackbar.LENGTH_SHORT
        ).show()
    }

    override fun invalidateOptionsMenu() {
        this.activity?.invalidateOptionsMenu()
    }

    // endregion
}