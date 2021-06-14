package com.mohamed.zakria.librarian.database.dao

import androidx.room.*
import com.mohamed.zakria.librarian.model.ReadingList
import kotlinx.coroutines.flow.Flow

@Dao
interface ReadingListDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun addReadingList(readingList: ReadingList)

  @Query("SELECT * FROM readinglist")
  suspend fun getReadingLists(): List<ReadingList>

  @Query("SELECT * FROM readinglist")
  fun getReadingListsFlow(): Flow<List<ReadingList>>

  @Delete
  suspend fun removeReadingList(readingList: ReadingList)

  @Query("SELECT * FROM readinglist WHERE id = :listId")
  suspend fun getReadingListById(listId: String): ReadingList

  @Update(onConflict = OnConflictStrategy.REPLACE)
  suspend fun updateReadingList(newReadingList: ReadingList)
}