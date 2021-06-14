package com.mohamed.zakria.librarian.model.relations

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Relation
import com.mohamed.zakria.librarian.model.Book
import com.mohamed.zakria.librarian.model.Review
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BookReview(
    @Embedded
    val review: Review,
    @Relation(
        parentColumn = "bookId",
        entityColumn = "id"
    )
    val book: Book
) : Parcelable