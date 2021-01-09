package amit.green.moviesme.api.dao

import amit.green.moviesme.api.model.Movie
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MovieDao {
    @Query("SELECT * FROM favoriteMovies")
    fun getAll(): List<Movie>

    @Insert
    fun insertAll(vararg movies: Movie)

    @Delete
    fun delete(movie: Movie)

    @Query("DELETE FROM favoriteMovies WHERE imdbID = :imdbID")
    fun deleteById(imdbID: String)
}