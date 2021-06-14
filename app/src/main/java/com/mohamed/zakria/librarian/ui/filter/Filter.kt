package com.mohamed.zakria.librarian.ui.filter

sealed class Filter

class ByGenre(val genreId: String) : Filter()

class ByRating(val rating: Int) : Filter()