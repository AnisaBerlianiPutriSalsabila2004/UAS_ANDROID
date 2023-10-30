package com.example.projek_alsa

import android.os.Parcel
import android.os.Parcelable

data class film(val bg_img:Int, val ico_img:Int, val genre: String?, val name: String?, val sutradara:String?, val rate:Int, val rate_txt:String?, val deskripsiFilm: String?, val durasi: String?, val umur: String?) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(bg_img)
        parcel.writeInt(ico_img)
        parcel.writeString(genre)
        parcel.writeString(name)
        parcel.writeString(sutradara)
        parcel.writeInt(rate)
        parcel.writeString(rate_txt)
        parcel.writeString(deskripsiFilm)
        parcel.writeString(durasi)
        parcel.writeString(umur)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<film> {
        override fun createFromParcel(parcel: Parcel): film {
            return film(parcel)
        }

        override fun newArray(size: Int): Array<film?> {
            return arrayOfNulls(size)
        }
    }
}
