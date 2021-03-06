package com.mohamed.zakria.librarian.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.mohamed.zakria.R
import com.mohamed.zakria.librarian.ui.books.BooksFragment
import com.mohamed.zakria.librarian.ui.readingList.ReadingListFragment
import com.mohamed.zakria.librarian.ui.reviews.BookReviewsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

  private var reviewsFragment: BookReviewsFragment? = null
  private var readingListFragment: ReadingListFragment? = null
  private var booksFragment: BooksFragment? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    setTheme(R.style.AppTheme)

    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    initUi()

    if (savedInstanceState == null) {
      displayNextFragment(R.id.books)
    }
  }

  private fun initUi() {
    bottomNavigation.setOnNavigationItemSelectedListener { menuItem ->
      displayNextFragment(menuItem.itemId)
      true
    }
  }

  private fun displayNextFragment(itemId: Int) {
    when (itemId) {
      R.id.readingList -> {
        if (readingListFragment == null) {
          readingListFragment = ReadingListFragment()
        }

        displayFragment(readingListFragment!!)
      }

      R.id.bookReviews -> {
        if (reviewsFragment == null) {
          reviewsFragment = BookReviewsFragment()
        }

        displayFragment(reviewsFragment!!)
      }

      R.id.books -> {
        if (booksFragment == null) {
          booksFragment = BooksFragment()
        }

        displayFragment(booksFragment!!)
      }
    }
  }

  private fun displayFragment(fragment: Fragment) {
    supportFragmentManager.beginTransaction()
        .replace(R.id.fragmentContainer, fragment, null)
        .commit()
  }
}
