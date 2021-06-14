package com.mohamed.zakria.librarian.ui.readingList

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mohamed.zakria.R
import androidx.recyclerview.widget.RecyclerView
import com.mohamed.zakria.librarian.model.relations.ReadingListsWithBooks

class ReadingListAdapter(
    private val onItemSelected: (ReadingListsWithBooks) -> Unit,
    private val onItemLongTapped: (ReadingListsWithBooks) -> Unit
) : RecyclerView.Adapter<ReadingListViewHolder>() {

  private val items = mutableListOf<ReadingListsWithBooks>()

  fun setData(newItems: List<ReadingListsWithBooks>) {
    items.clear()
    items.addAll(newItems)
    notifyDataSetChanged()
  }

  override fun getItemCount() = items.size

  override fun onBindViewHolder(holder: ReadingListViewHolder, position: Int) {
    holder.showData(items[position], onItemSelected, onItemLongTapped)
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReadingListViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.item_reading_list, parent,
        false)

    return ReadingListViewHolder(view)
  }
}