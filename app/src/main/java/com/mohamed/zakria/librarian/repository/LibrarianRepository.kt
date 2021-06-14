package com.mohamed.zakria.librarian.repository

import com.mohamed.zakria.librarian.model.Book
import com.mohamed.zakria.librarian.model.Genre
import com.mohamed.zakria.librarian.model.ReadingList
import com.mohamed.zakria.librarian.model.Review
import com.mohamed.zakria.librarian.model.relations.BookAndGenre
import com.mohamed.zakria.librarian.model.relations.BookReview
import com.mohamed.zakria.librarian.model.relations.ReadingListsWithBooks
import kotlinx.coroutines.flow.Flow

interface LibrarianRepository {

  suspend fun addBook(book: Book)

  suspend fun getBooks(): List<BookAndGenre>

  suspend fun getBookById(bookId: String): BookAndGenre

  suspend fun removeBook(book: Book)

  suspend fun getGenres(): List<Genre>

  suspend fun getGenreById(genreId: String): Genre

  suspend fun addGenres(genres: List<Genre>)

  suspend fun addReview(review: Review)

  suspend fun removeReview(review: Review)

  suspend fun getReviewById(reviewId: String): BookReview

  suspend fun getReviews(): List<BookReview>

  fun getReviewsFlow(): Flow<List<BookReview>>

  suspend fun updateReview(review: Review)

  suspend fun addReadingList(readingList: ReadingList)

  suspend fun getReadingLists(): List<ReadingListsWithBooks>

  fun getReadingListsFlow(): Flow<List<ReadingListsWithBooks>>

  suspend fun getReadingListById(listId: String): ReadingListsWithBooks

  suspend fun updateReadingList(newReadingList: ReadingList)

  suspend fun removeReadingList(readingList: ReadingList)

  suspend fun getBooksByGenre(genreId: String): List<BookAndGenre>

  suspend fun getBooksByRating(rating: Int): List<BookAndGenre>
}