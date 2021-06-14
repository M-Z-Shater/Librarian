package com.mohamed.zakria.librarian.ui.readingList

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.mohamed.zakria.R
import com.mohamed.zakria.librarian.model.relations.ReadingListsWithBooks
import kotlinx.android.synthetic.main.item_reading_list.view.*

class ReadingListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

  fun showData(readingList: ReadingListsWithBooks,
               onItemSelected: (ReadingListsWithBooks) -> Unit,
               onItemLongTapped: (ReadingListsWithBooks) -> Unit) = with(itemView) {
    readingListTitle.text = readingList.name
    readingListNumberOfBooks.text = context.getString(R.string.reading_list_number_of_books,
        readingList.books.size)

    setOnClickListener { onItemSelected(readingList) }
    setOnLongClickListener {
      onItemLongTapped(readingList)
      true
    }
  }
}