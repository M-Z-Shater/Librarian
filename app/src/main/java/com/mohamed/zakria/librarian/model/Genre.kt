package com.mohamed.zakria.librarian.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity
class Genre(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    val name: String
) : Parcelable