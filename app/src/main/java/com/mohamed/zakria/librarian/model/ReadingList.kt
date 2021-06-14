package com.mohamed.zakria.librarian.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.mohamed.zakria.librarian.database.converters.BookIdsConverter
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity
class ReadingList(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    @TypeConverters(BookIdsConverter::class)
    val bookIds: List<String>
) : Parcelable