package com.mohamed.zakria.librarian.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mohamed.zakria.librarian.database.converters.BookIdsConverter
import com.mohamed.zakria.librarian.database.converters.DateConverter
import com.mohamed.zakria.librarian.database.converters.ReadingEntryConverter
import com.mohamed.zakria.librarian.database.dao.BookDao
import com.mohamed.zakria.librarian.database.dao.GenreDao
import com.mohamed.zakria.librarian.database.dao.ReadingListDao
import com.mohamed.zakria.librarian.database.dao.ReviewDao
import com.mohamed.zakria.librarian.database.migrations.migration_1_2
import com.mohamed.zakria.librarian.database.migrations.migration_2_3
import com.mohamed.zakria.librarian.database.migrations.migration_3_4
import com.mohamed.zakria.librarian.model.Book
import com.mohamed.zakria.librarian.model.Genre
import com.mohamed.zakria.librarian.model.ReadingList
import com.mohamed.zakria.librarian.model.Review

const val DATABASE_VERSION = 4

@Database(
    entities = [Book::class, Genre::class, ReadingList::class, Review::class],
    version = DATABASE_VERSION
)
@TypeConverters(DateConverter::class, ReadingEntryConverter::class,BookIdsConverter::class)
abstract class LibrarianDatabase : RoomDatabase() {

  companion object {
    private const val DATABASE_NAME = "Librarian"

    fun buildDatabase(context: Context): LibrarianDatabase {
      return Room.databaseBuilder(
          context,
          LibrarianDatabase::class.java,
          DATABASE_NAME
      )
          .addMigrations(migration_1_2, migration_2_3, migration_3_4)
          .build()
    }
  }

  abstract fun bookDao(): BookDao

  abstract fun genreDao(): GenreDao

  abstract fun reviewDao(): ReviewDao

  abstract fun readingListDao(): ReadingListDao
}