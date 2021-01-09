package amit.green.moviesme.api.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName


data class Title (
    @SerializedName("Title")
    val title: String?,

    @SerializedName("Year")
    val year: String?,

    val imdbID: String?,

    @SerializedName("Type")
    val type: Type?,

    @SerializedName("Poster")
    val poster: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readParcelable(Type::class.java.classLoader),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(year)
        parcel.writeString(imdbID)
        parcel.writeParcelable(type, flags)
        parcel.writeString(poster)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Title> {
        override fun createFromParcel(parcel: Parcel): Title {
            return Title(parcel)
        }

        override fun newArray(size: Int): Array<Title?> {
            return arrayOfNulls(size)
        }
    }
}

enum class Type : Parcelable {

    @SerializedName("movie")
    Movie,

    @SerializedName("series")
    Series,

    @SerializedName("episode")
    Episode;

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(Gson().toJson(this))
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Type> {
        override fun createFromParcel(parcel: Parcel): Type {
            return Gson().fromJson(parcel.readString(), Type::class.java)
        }

        override fun newArray(size: Int): Array<Type?> {
            return arrayOfNulls(size)
        }
    }
}