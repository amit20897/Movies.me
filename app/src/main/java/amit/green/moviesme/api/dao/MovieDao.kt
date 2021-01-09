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

    @Query("SELECT * FROM favoriteMovies WHERE imdbID IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<Movie>

    @Insert
    fun insertAll(vararg users: Movie)

    @Delete
    fun delete(user: Movie)
}