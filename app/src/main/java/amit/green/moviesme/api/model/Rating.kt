package amit.green.moviesme.api.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity
data class Rating (
    @SerializedName("Source")
    val source: String,

    @SerializedName("Value")
    val value: String
)
