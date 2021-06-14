package com.mohamed.zakria.librarian.model.relations

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ReadingListsWithBooks(
    val id: String,
    val name: String,
    val books: List<BookAndGenre>
) : Parcelable