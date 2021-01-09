package amit.green.moviesme.api.dao

import amit.green.moviesme.api.model.Movie
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Movie::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        var db: AppDatabase? = null
            private set

        fun create(context: Context): AppDatabase {
            val db = db
            if (db != null) return db

            val newDB = Room.databaseBuilder(
                context,
                AppDatabase::class.java, "movies-me"
            ).allowMainThreadQueries().build()

            this.db = newDB
            return newDB
        }
    }
}