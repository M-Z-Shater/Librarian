package com.mohamed.zakria.librarian.ui.books

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mohamed.zakria.R
import com.mohamed.zakria.librarian.model.Book
import com.mohamed.zakria.librarian.model.relations.BookAndGenre

class BookAdapter(
    private val onItemLongTapped: (Book) -> Unit
) : RecyclerView.Adapter<BookViewHolder>() {

  private val items = mutableListOf<BookAndGenre>()

  fun setData(newItems: List<BookAndGenre>) {
    items.clear()
    items.addAll(newItems)
    notifyDataSetChanged()
  }

  override fun getItemCount() = items.size

  override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
    holder.showData(items[position], onItemLongTapped)
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent, false)

    return BookViewHolder(view)
  }
}