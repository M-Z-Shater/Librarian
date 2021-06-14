package com.mohamed.zakria.librarian.ui.bookPicker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.mohamed.zakria.R
import com.mohamed.zakria.librarian.App
import com.mohamed.zakria.librarian.model.BookItem
import kotlinx.android.synthetic.main.dialog_add_book.*
import kotlinx.coroutines.launch

class BookPickerDialogFragment(private val onItemSelected: (String) -> Unit) : DialogFragment() {

  private val adapter by lazy { BookItemAdapter() }
  private val repository by lazy { App.repository }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                            savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.dialog_add_book, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    setStyle(STYLE_NO_TITLE, R.style.FragmentDialogTheme)

    initUi()
  }

  private fun initUi() = lifecycleScope.launch {
    val books = repository.getBooks().map { BookItem(it.book.id, it.book.name, false) }

    bookOptionsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    bookOptionsRecyclerView.adapter = adapter

    adapter.setData(books)

    addBook.setOnClickListener {
      addBookToReadingList()
    }
  }

  private fun addBookToReadingList() {
    val selectedItem = adapter.getSelectedItem()
    if (selectedItem.isNotBlank()) {
      onItemSelected(selectedItem)
      dismissAllowingStateLoss()
    }
  }

  override fun onStart() {
    super.onStart()
    dialog?.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT,
        WindowManager.LayoutParams.WRAP_CONTENT)
  }
}