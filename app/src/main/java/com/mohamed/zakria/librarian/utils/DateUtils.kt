package com.mohamed.zakria.librarian.utils

import java.text.SimpleDateFormat
import java.util.*

private val simpleDateFormat = SimpleDateFormat.getDateTimeInstance()

fun formatDateToText(date: Date): String = simpleDateFormat.format(date)