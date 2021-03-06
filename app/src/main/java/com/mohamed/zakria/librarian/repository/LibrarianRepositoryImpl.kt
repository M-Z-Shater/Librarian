package com.mohamed.zakria.librarian.repository

import com.mohamed.zakria.librarian.database.dao.BookDao
import com.mohamed.zakria.librarian.database.dao.GenreDao
import com.mohamed.zakria.librarian.database.dao.ReadingListDao
import com.mohamed.zakria.librarian.database.dao.ReviewDao
import com.mohamed.zakria.librarian.model.Book
import com.mohamed.zakria.librarian.model.Genre
import com.mohamed.zakria.librarian.model.ReadingList
import com.mohamed.zakria.librarian.model.Review
import com.mohamed.zakria.librarian.model.relations.BookAndGenre
import com.mohamed.zakria.librarian.model.relations.BookReview
import com.mohamed.zakria.librarian.model.relations.ReadingListsWithBooks
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LibrarianRepositoryImpl(
    private val bookDao: BookDao,
    private val genreDao: GenreDao,
    private val readingListDao: ReadingListDao,
    private val reviewDao: ReviewDao
) : LibrarianRepository {

  override suspend fun addBook(book: Book) = bookDao.addBook(book)

  override suspend fun getBooks(): List<BookAndGenre> = bookDao.getBooks()

  override suspend fun getBookById(bookId: String): BookAndGenre = bookDao.getBookById(bookId)

  override suspend fun removeBook(book: Book) = bookDao.removeBook(book)

  override suspend fun getGenres(): List<Genre> = genreDao.getGenres()

  override suspend fun getGenreById(genreId: String): Genre = genreDao.getGenreById(genreId)

  override suspend fun addGenres(genres: List<Genre>) = genreDao.addGenres(genres)

  override suspend fun addReview(review: Review) = reviewDao.addReview(review)

  override suspend fun removeReview(review: Review) = reviewDao.removeReview(review)

  override suspend fun getReviewById(reviewId: String): BookReview = reviewDao.getReviewById(reviewId)

  override suspend fun getReviews(): List<BookReview> = reviewDao.getReviews()

  override fun getReviewsFlow(): Flow<List<BookReview>> = reviewDao.getReviewsFlow()

  override suspend fun updateReview(review: Review) = reviewDao.updateReview(review)

  override suspend fun addReadingList(readingList: ReadingList) = readingListDao.addReadingList(readingList)

  override suspend fun getReadingLists(): List<ReadingListsWithBooks> =
      readingListDao.getReadingLists().map { readingList ->
        ReadingListsWithBooks(
            readingList.id,
            readingList.name,
            readingList.bookIds.map { getBookById(it) }
        )
      }

  override fun getReadingListsFlow(): Flow<List<ReadingListsWithBooks>> =
      readingListDao.getReadingListsFlow().map { items ->
        items.map { readingList ->
          ReadingListsWithBooks(
              readingList.id,
              readingList.name,
              readingList.bookIds.map { getBookById(it) }
          )
        }
      }

  override suspend fun getReadingListById(listId: String): ReadingListsWithBooks {
    return readingListDao.getReadingListById(listId).let { readingList ->
      ReadingListsWithBooks(
          readingList.id,
          readingList.name,
          readingList.bookIds.map { getBookById(it) }
      )
    }
  }

  override suspend fun updateReadingList(newReadingList: ReadingList) {
    readingListDao.updateReadingList(newReadingList)
  }

  override suspend fun removeReadingList(readingList: ReadingList) =
      readingListDao.removeReadingList(readingList)


  override suspend fun getBooksByGenre(genreId: String): List<BookAndGenre> =
      genreDao.getBooksByGenre(genreId).let { booksByGenre ->
        val books = booksByGenre.books ?: return emptyList()

        return books.map { BookAndGenre(it, booksByGenre.genre) }
      }

  override suspend fun getBooksByRating(rating: Int): List<BookAndGenre> {
    val reviewsByRating = reviewDao.getReviewsByRating(rating)

    return reviewsByRating.map { BookAndGenre(it.book, genreDao.getGenreById(it.book.genreId)) }
  }
}