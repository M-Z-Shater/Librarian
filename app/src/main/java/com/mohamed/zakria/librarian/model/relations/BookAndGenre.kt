package com.mohamed.zakria.librarian.model.relations

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Relation
import com.mohamed.zakria.librarian.model.Book
import com.mohamed.zakria.librarian.model.Genre
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BookAndGenre(
    @Embedded
    val book: Book,
    @Relation(parentColumn = "bookGenreId", entityColumn = "id")
    val genre: Genre
) : Parcelable