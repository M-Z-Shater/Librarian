package com.mohamed.zakria.librarian.database.dao

import androidx.room.*
import com.mohamed.zakria.librarian.model.Genre
import com.mohamed.zakria.librarian.model.relations.BooksByGenre

@Dao
interface GenreDao {

  @Query("SELECT * FROM genre")
  suspend fun getGenres(): List<Genre>

  @Query("SELECT * FROM genre WHERE id = :genreId")
  suspend fun getGenreById(genreId: String): Genre

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun addGenres(genres: List<Genre>)

  @Transaction
  @Query("SELECT * FROM genre WHERE id = :genreId")
  suspend fun getBooksByGenre(genreId: String): BooksByGenre

  @Transaction
  @Query("SELECT * FROM genre")
  suspend fun getBooksByGenres(): List<BooksByGenre>
}