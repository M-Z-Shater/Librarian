
package com.mohamed.zakria.librarian.ui.books

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.mohamed.zakria.R
import com.mohamed.zakria.librarian.App
import com.mohamed.zakria.librarian.model.Book
import com.mohamed.zakria.librarian.ui.addBook.AddBookActivity
import com.mohamed.zakria.librarian.ui.filter.ByGenre
import com.mohamed.zakria.librarian.ui.filter.ByRating
import com.mohamed.zakria.librarian.ui.filter.Filter
import com.mohamed.zakria.librarian.ui.filter.FilterPickerDialogFragment
import com.mohamed.zakria.librarian.utils.createAndShowDialog
import kotlinx.android.synthetic.main.fragment_books.*
import kotlinx.android.synthetic.main.fragment_reviews.pullToRefresh
import kotlinx.coroutines.launch

private const val REQUEST_CODE_ADD_BOOK = 101

class BooksFragment : Fragment() {

  private val adapter by lazy { BookAdapter(::onItemLongTapped) }
  private var filter: Filter? = null
  private val repository by lazy { App.repository }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                            savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_books, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initUi()
  }

  override fun onStart() {
    super.onStart()
    loadBooks()
  }

  private fun initUi() {
    pullToRefresh.setOnRefreshListener {
      loadBooks()
    }

    booksRecyclerView.adapter = adapter
    booksRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    addBook.setOnClickListener {
      startActivityForResult(AddBookActivity.getIntent(requireContext()), REQUEST_CODE_ADD_BOOK)
    }

    filterBooks.setOnClickListener {
      val dialog = FilterPickerDialogFragment { filter ->
        this.filter = filter

        loadBooks()
      }

      dialog.show(requireFragmentManager(), null)
    }
  }

  private fun loadBooks() = lifecycleScope.launch {
    pullToRefresh.isRefreshing = true

    val books = when (val currentFilter = filter) {
      is ByGenre -> repository.getBooksByGenre(currentFilter.genreId)
      is ByRating -> repository.getBooksByRating(currentFilter.rating)
      else -> repository.getBooks()
    }

    adapter.setData(books)
    pullToRefresh.isRefreshing = false
  }

  private fun onItemLongTapped(book: Book) {
    createAndShowDialog(requireContext(),
        getString(R.string.delete_title),
        getString(R.string.delete_message, book.name),
        onPositiveAction = { removeBook(book) }
    )
  }

  private fun removeBook(book: Book) {
    lifecycleScope.launch {
      repository.removeBook(book)
      loadBooks()
    }
  }
}