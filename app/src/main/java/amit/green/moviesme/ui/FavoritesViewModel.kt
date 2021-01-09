package amit.green.moviesme.ui

import amit.green.moviesme.api.dao.AppDatabase
import amit.green.moviesme.api.model.Movie
import androidx.lifecycle.ViewModel

class FavoritesViewModel : ViewModel() {
    private var favorites: ArrayList<Movie> = ArrayList(AppDatabase.db?.movieDao()?.getAll() ?: listOf())
    private var favoritesSet: MutableSet<String>

    init {
        favoritesSet = favorites.map { it.imdbID }.toMutableSet()
    }

    fun isFavorite(movie: Movie): Boolean {
        return favoritesSet.contains(movie.imdbID)
    }

    fun isFavorite(imdbID: String): Boolean {
        return favoritesSet.contains(imdbID)
    }

    fun addFavorite(movie: Movie) {
        if (favoritesSet.contains(movie.imdbID)) return

        favoritesSet.add(movie.imdbID)
        favorites.add(movie)
        AppDatabase.db?.movieDao()?.insertAll(movie)
    }

    fun removeFavorite(movie: Movie) {
        if (!favoritesSet.contains(movie.imdbID)) return

        favoritesSet.remove(movie.imdbID)
        favorites.removeAll { it.imdbID == movie.imdbID }
        AppDatabase.db?.movieDao()?.delete(movie)
    }

    fun removeFavorite(imdbID: String) {
        if (!favoritesSet.contains(imdbID)) return

        favoritesSet.remove(imdbID)
        favorites.removeAll { it.imdbID == imdbID }
        AppDatabase.db?.movieDao()?.deleteById(imdbID)
    }
}