package com.mohamed.zakria.librarian.database.dao

import androidx.room.*
import com.mohamed.zakria.librarian.model.Book
import com.mohamed.zakria.librarian.model.relations.BookAndGenre

@Dao
interface BookDao {

  @Query("SELECT * FROM books")
  suspend fun getBooks(): List<BookAndGenre>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun addBook(book: Book)

  @Delete
  suspend fun removeBook(book: Book)

  @Query("SELECT * FROM books WHERE id = :bookId")
  suspend fun getBookById(bookId: String): BookAndGenre
}