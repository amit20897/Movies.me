package amit.green.moviesme.ui.home

import amit.green.moviesme.R
import amit.green.moviesme.api.model.Title
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_movie.view.*

class MoviesAdapter(movies: List<Title>?) : RecyclerView.Adapter<MoviesAdapter.MovieHolder>() {

    var movies: List<Title> = movies ?: listOf()
        private set

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieHolder(view)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int = movies.size

    fun setMovies(movies: List<Title>) {
        this.movies = movies
        this.notifyDataSetChanged()
    }

    class MovieHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var image = view.imageView
        private var ratingTextView = view.ratingTextView

        fun bind(title: Title) {
            ratingTextView.text = "sdfsdf"
            Glide.with(image)
                .load(title.poster)
                .centerCrop()
                .placeholder(R.drawable.bg_rating)
                .into(image)
        }
    }
}