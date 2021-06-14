package com.mohamed.zakria.librarian.model.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.mohamed.zakria.librarian.model.Book
import com.mohamed.zakria.librarian.model.Genre

class BooksByGenre(
    @Embedded
    val genre: Genre,
    @Relation(parentColumn = "id", entityColumn = "bookGenreId")
    val books: List<Book>?
)