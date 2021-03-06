package com.mohamed.zakria.librarian.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class ReadingEntry(
    val id: String = UUID.randomUUID().toString(),
    val comment: String,
    val dateOfEntry: Date
) : Parcelable