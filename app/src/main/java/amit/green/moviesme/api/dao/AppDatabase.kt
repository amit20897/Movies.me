package amit.green.moviesme.api.dao

import amit.green.moviesme.api.model.Movie
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Movie::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        private var db: RoomDatabase? = null

        fun get(context: Context): RoomDatabase {
            return db ?: Room.databaseBuilder(
                context,
                AppDatabase::class.java, "movies-me"
            ).build()
        }
    }
}