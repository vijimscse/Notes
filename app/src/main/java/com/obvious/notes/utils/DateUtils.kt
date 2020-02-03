package com.obvious.notes.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
    @JvmStatic
    fun toSimpleString(date: Date) : String {
        val format = SimpleDateFormat("dd MMMM yyyy hh:mm aa")

        return format.format(date)
    }
}