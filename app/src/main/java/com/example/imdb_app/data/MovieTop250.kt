package com.example.imdb_app.data

import android.os.Parcel
import android.os.Parcelable

data class MovieTop250(
    val id: String,
    val rank: String,
    val title: String,
    val fullTitle: String,
    val year: String,
    val image: String,
    val crew: String,
    val imDbRating: String,
    val imDbRatingCount: String
):Parcelable {
    constructor(parcel: Parcel) : this(
        id=parcel.readString()!!,
        rank= parcel.readString()!!,
        title= parcel.readString()!!,
        fullTitle= parcel.readString()!!,
        year= parcel.readString()!!,
        image= parcel.readString()!!,
        crew= parcel.readString()!!,
        imDbRating= parcel.readString()!!,
        imDbRatingCount= parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(rank)
        parcel.writeString(title)
        parcel.writeString(fullTitle)
        parcel.writeString(year)
        parcel.writeString(image)
        parcel.writeString(crew)
        parcel.writeString(imDbRating)
        parcel.writeString(imDbRatingCount)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MovieTop250> {
        override fun createFromParcel(parcel: Parcel): MovieTop250 {
            return MovieTop250(parcel)
        }

        override fun newArray(size: Int): Array<MovieTop250?> {
            return arrayOfNulls(size)
        }
    }
}
