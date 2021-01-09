package amit.green.moviesme.ui.home

import amit.green.moviesme.R
import amit.green.moviesme.api.model.Title
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_movie.view.*

class MoviesAdapter(
    movies: List<Title>?, var listener: MoviesAdapterListener?
) : RecyclerView.Adapter<MoviesAdapter.MovieHolder>() {

    var movies: List<Title> = movies ?: listOf()
        private set

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieHolder(view)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        val title = movies[position]
        holder.bind(title, position)
        listener?.onLastItemReached(this)
    }

    override fun getItemCount(): Int = movies.size

    fun setMovies(movies: List<Title>) {
        this.movies = movies
        this.notifyDataSetChanged()
    }

    fun addMovies(movies: List<Title>) {
        val lastPos = this.movies.size
        this.movies += movies
        this.notifyItemRangeInserted(lastPos, movies.size)
    }

    inner class MovieHolder(var view: View) : RecyclerView.ViewHolder(view) {
        private var image = view.imageView
        private var ratingTextView = view.ratingTextView

        fun bind(title: Title, position: Int) {
            ratingTextView.text = ""
            Glide.with(image)
                .load(title.poster)
                .centerCrop()
                .placeholder(R.drawable.bg_rating)
                .into(image)
            view.setOnClickListener {
                listener?.onItemClick(this@MoviesAdapter, title, position)
            }
        }
    }

    interface MoviesAdapterListener {
        fun onItemClick(adapter: MoviesAdapter, item: Title, position: Int)
        fun onLastItemReached(adapter: MoviesAdapter)
    }
}