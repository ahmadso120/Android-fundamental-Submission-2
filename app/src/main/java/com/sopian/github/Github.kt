package com.sopian.github

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parceler
import kotlinx.parcelize.Parcelize

@Parcelize
data class Github(
    val username: String,
    val name: String,
    val avatar: Int,
    val company: String,
    val location: String,
    val repository: Int,
    val follower: Int,
    val following: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readValue(Int::class.java.classLoader) as Int,
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readValue(Int::class.java.classLoader) as Int,
        parcel.readValue(Int::class.java.classLoader) as Int,
        parcel.readValue(Int::class.java.classLoader) as Int,
    )

    companion object : Parceler<Github> {
        override fun create(parcel: Parcel): Github {
            return Github(parcel)
        }

        override fun Github.write(parcel: Parcel, flags: Int) {
            parcel.writeString(username)
            parcel.writeString(name)
            parcel.writeValue(avatar)
            parcel.writeString(company)
            parcel.writeString(location)
            parcel.writeValue(repository)
            parcel.writeValue(follower)
            parcel.writeValue(following)
        }

    }
}