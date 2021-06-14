package com.mohamed.zakria.librarian

import android.app.Application
import com.google.gson.Gson
import com.mohamed.zakria.librarian.database.LibrarianDatabase
import com.mohamed.zakria.librarian.model.Genre
import com.mohamed.zakria.librarian.repository.LibrarianRepository
import com.mohamed.zakria.librarian.repository.LibrarianRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class App : Application() {

  companion object {
    private lateinit var instance: App

    private val database: LibrarianDatabase by lazy {
      LibrarianDatabase.buildDatabase(instance)
    }

    val repository: LibrarianRepository by lazy {
      LibrarianRepositoryImpl(
          database.bookDao(),
          database.genreDao(),
          database.readingListDao(),
          database.reviewDao()
      )
    }

    val gson by lazy { Gson() }
  }

  override fun onCreate() {
    super.onCreate()
    instance = this

    GlobalScope.launch(Dispatchers.Main.immediate) {
      if (repository.getGenres().isEmpty()) {
        repository.addGenres(
            listOf(
                Genre(name = "Action"),
                Genre(name = "Adventure"),
                Genre(name = "Classic"),
                Genre(name = "Mystery"),
                Genre(name = "Fantasy"),
                Genre(name = "Sci-Fi"),
                Genre(name = "History"),
                Genre(name = "Horror"),
                Genre(name = "Romance"),
                Genre(name = "Short Story"),
                Genre(name = "Biography"),
                Genre(name = "Poetry"),
                Genre(name = "Self-Help"),
                Genre(name = "Young novel")
            )
        )
      }
    }
  }
}