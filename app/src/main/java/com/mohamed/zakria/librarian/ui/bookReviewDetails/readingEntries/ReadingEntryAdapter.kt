package com.mohamed.zakria.librarian.ui.bookReviewDetails.readingEntries

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mohamed.zakria.R
import com.mohamed.zakria.librarian.model.ReadingEntry

class ReadingEntryAdapter(private val onItemLongTapped: (ReadingEntry) -> Unit)
  : RecyclerView.Adapter<ReadingEntryViewHolder>() {

  private val items = mutableListOf<ReadingEntry>()

  fun setData(newItems: List<ReadingEntry>) {
    items.clear()
    items.addAll(newItems)
    notifyDataSetChanged()
  }

  override fun getItemCount() = items.size

  override fun onBindViewHolder(holder: ReadingEntryViewHolder, position: Int) {
    holder.showData(items[position], onItemLongTapped)
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReadingEntryViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(
      R.layout.item_reading_entry, parent,
        false)

    return ReadingEntryViewHolder(view)
  }
}